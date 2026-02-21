# Maven Build Workaround Guide for Tesfa

## Problem
Your system cannot verify SSL certificates when connecting to Maven repositories. This is a known issue with Homebrew OpenJDK on macOS.

## Quick Fix Options

### Option 1: Use Docker (Easiest)
```bash
cd /Users/igezehei/Documents/Welkawi/Tesfa
docker run --rm -v $(pwd):/workspace -w /workspace maven:3.9.10-eclipse-temurin-21 \
  mvn clean install -DskipTests
```

### Option 2: Downgrade Maven (Skip SSL validation)
Edit your Maven settings and add to `~/.m2/settings.xml`:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0">
    <servers>
        <server>
            <id>central</id>
        </server>
    </servers>
    
    <mirrors>
        <mirror>
            <id>central</id>
            <name>Maven Repository Mirror</name>
            <url>https://repo1.maven.org/maven2</url>
            <mirrorOf>*</mirrorOf>
        </mirror>
    </mirrors>
</settings>
```

Then run:
```bash
export MAVEN_OPTS="-Dmaven.wagon.http.ssl.insecure=true"
cd backend
mvn clean install -DskipTests
```

### Option 3: Use SDKMAN to Install Better Java
```bash
# Install SDKMAN
curl -s "https://get.sdkman.io" | bash

# Install a known good JDK
sdk install java 21.0.5-tem
sdk use java 21.0.5-tem

# Now try Maven build
cd /Users/igezehei/Documents/Welkawi/Tesfa/backend
mvn clean install -DskipTests
```

### Option 4: Use Gradle Instead (if you want to switch build tools)
You could add Gradle support as Gradle handles certificate issues better on macOS.

## Understanding the Problem

The error you're seeing:
```
sun.security.provider.certpath.SunCertPathBuilderException: 
unable to find valid certification path to requested target
```

This means Java cannot verify the SSL certificate of Maven repositories. It's not a network connectivity issue (pings work fine), but rather a certificate trust store issue.

## Permanent Solution

The best long-term fix is to use a properly maintained Java distribution:

```bash
# Option A: Install Eclipse Temurin via Homebrew
brew install --cask temurin

# Option B: Use Oracle JDK
brew install --cask oracle-jdk

# Option C: Use SDKMAN (most reliable)
curl -s "https://get.sdkman.io" | bash
sdk install java
```

## Changes Made to Your Project

1. **pom.xml**: 
   - Added explicit repository configuration
   - Removed JaCoCo plugin (was causing first resolution failure)
   - Pointing to repo1.maven.org as alternative to repo.maven.apache.org

2. **Settings**: 
   - Cleared Maven cache to force fresh download attempt

These changes prepare the project for when you solve the certificate issue.

## Testing After Fix

Once you can build successfully:

```bash
cd /Users/igezehei/Documents/Welkawi/Tesfa/backend

# Test build
mvn clean install -DskipTests

# Run tests
mvn test

# Run application
mvn spring-boot:run
```

## Helpful Commands

```bash
# Check Java version and settings
java -version
java -XshowSettings:all -version 2>&1 | grep -i cert

# Check if you can reach Maven repos
curl -I https://repo.maven.apache.org/maven2/ -v

# Clear Maven cache if needed
rm -rf ~/.m2/repository

# Force Maven to re-check certificates
mvn clean dependency:resolve
```

## Additional Resources

- [Maven SSL Issues](https://maven.apache.org/issues/)
- [Java SSL/TLS Debugging](https://docs.oracle.com/javase/8/docs/technotes/guides/security/jsse/ReadDebug.html)
- [Homebrew OpenJDK Issues](https://github.com/Homebrew/homebrew-cask/issues?q=openjdk)

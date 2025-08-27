#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"
SRC_DIR="$ROOT_DIR/src/main/java"
OUT_DIR="$ROOT_DIR/target/classes"
JAR_DIR="$ROOT_DIR/target"
MAIN_CLASS="com.example.minimal.App"

mkdir -p "$OUT_DIR"
mkdir -p "$JAR_DIR"

# Compile .java files
find "$SRC_DIR" -name "*.java" > /tmp/tesfa_minimal_sources.txt
javac -d "$OUT_DIR" @/tmp/tesfa_minimal_sources.txt

# Create jar
cd "$OUT_DIR"
jar --create --file="$JAR_DIR/tesfa-minimal.jar" --manifest=<(echo "Main-Class: $MAIN_CLASS") -C "$OUT_DIR" .

echo "Built $JAR_DIR/tesfa-minimal.jar"

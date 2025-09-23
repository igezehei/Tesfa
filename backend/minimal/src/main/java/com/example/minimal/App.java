package com.example.minimal;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Minimal application entry used as a simple build target and smoke test.
 * Improved to use java.util.logging and include basic timing and argument echoing.
 */
public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        logger.info("Tesfa minimal module - starting");

        if (args != null && args.length > 0) {
            logger.info("Args: " + String.join(" ", args));
        }

        // main work (currently none) — keep this minimal and fast
        logger.info("Tesfa minimal module - build OK");

        long elapsed = System.currentTimeMillis() - start;
        logger.log(Level.FINE, "Execution time: {0}ms", elapsed);
    }
}

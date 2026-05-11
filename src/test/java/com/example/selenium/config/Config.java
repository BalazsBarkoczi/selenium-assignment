package com.example.selenium.config;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {

    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    public static String get(String key) {
        String value = System.getenv(key);

        if (value == null || value.isBlank()) {
            value = dotenv.get(key);
        }

        return value;
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        String value = get(key);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        return Boolean.parseBoolean(value);
    }
}

package com.nexusteam.framework.xml.string.generator;

import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Builds Android resource names from string content using pattern matching.
 *
 * <pre>
 * "Welcome to Nexus"  → welcome_to_nexus
 * "Enter your name!"  → enter_your_name
 * "OK"                → ok
 * "123 start"         → s_123_start
 * "" / symbols only   → null (caller should fall back to sequential)
 * </pre>
 */
public final class SmartNameGenerator {

    public static final int MAX_LENGTH = 40;

    private static final Pattern NON_ALNUM = Pattern.compile("[^a-z0-9]+");
    private static final Pattern MULTI_UNDERSCORE = Pattern.compile("_+");
    private static final Pattern LEADING_TRAILING_US = Pattern.compile("^_+|_+$");

    private SmartNameGenerator() {
    }

    public static String fromValue(String value) {
        if (value == null) {
            return null;
        }
        String s = value.trim().toLowerCase(Locale.ROOT);
        if (s.isEmpty()) {
            return null;
        }

        s = NON_ALNUM.matcher(s).replaceAll("_");
        s = MULTI_UNDERSCORE.matcher(s).replaceAll("_");
        s = LEADING_TRAILING_US.matcher(s).replaceAll("");

        if (s.isEmpty()) {
            return null;
        }

        char first = s.charAt(0);
        if (first < 'a' || first > 'z') {
            s = "s_" + s;
        }

        if (s.length() > MAX_LENGTH) {
            s = s.substring(0, MAX_LENGTH);
            s = LEADING_TRAILING_US.matcher(s).replaceAll("");
            if (s.isEmpty()) {
                return null;
            }
            if (s.charAt(0) < 'a' || s.charAt(0) > 'z') {
                s = "s_" + s;
            }
        }

        return s;
    }
}

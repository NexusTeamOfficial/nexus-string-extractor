package com.nexusteam.framework.xml.string.generator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SmartNameGeneratorTest {

    @Test
    void simplePhrase() {
        assertEquals("welcome_to_nexus", SmartNameGenerator.fromValue("Welcome to Nexus"));
    }

    @Test
    void stripsPunctuation() {
        assertEquals("enter_your_name", SmartNameGenerator.fromValue("Enter your name!"));
    }

    @Test
    void shortOk() {
        assertEquals("ok", SmartNameGenerator.fromValue("OK"));
    }

    @Test
    void leadingDigitGetsPrefix() {
        assertEquals("s_123_start", SmartNameGenerator.fromValue("123 start"));
    }

    @Test
    void emptyOrSymbolsReturnNull() {
        assertNull(SmartNameGenerator.fromValue(""));
        assertNull(SmartNameGenerator.fromValue("   "));
        assertNull(SmartNameGenerator.fromValue("!!!"));
        assertNull(SmartNameGenerator.fromValue(null));
    }

    @Test
    void collapsesWhitespaceAndUnderscores() {
        assertEquals("hello_world", SmartNameGenerator.fromValue("  Hello   World  "));
    }

    @Test
    void respectsMaxLength() {
        String longText = "a".repeat(100);
        String name = SmartNameGenerator.fromValue(longText);
        assertNotNull(name);
        assertTrue(name.length() <= SmartNameGenerator.MAX_LENGTH);
    }
}

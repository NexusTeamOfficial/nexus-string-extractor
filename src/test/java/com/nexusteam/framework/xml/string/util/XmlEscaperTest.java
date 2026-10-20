package com.nexusteam.framework.xml.string.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XmlEscaperTest {

    @Test
    void escapesAll() {
        assertEquals("&amp;&lt;&gt;&quot;&apos;", XmlEscaper.escape("&<>\"'"));
    }

    @Test
    void plainUnchanged() {
        assertEquals("Hello", XmlEscaper.escape("Hello"));
    }

    @Test
    void nullThrows() {
        assertThrows(IllegalArgumentException.class, () -> XmlEscaper.escape(null));
    }
}

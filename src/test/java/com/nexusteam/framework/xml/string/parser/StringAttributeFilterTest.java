package com.nexusteam.framework.xml.string.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringAttributeFilterTest {

    @Test
    void targets() {
        assertTrue(StringAttributeFilter.isTargetAttribute(StringAttributeFilter.ANDROID_NS, "text"));
        assertTrue(StringAttributeFilter.isTargetAttribute(StringAttributeFilter.ANDROID_NS, "hint"));
        assertFalse(StringAttributeFilter.isTargetAttribute(StringAttributeFilter.ANDROID_NS, "id"));
    }

    @Test
    void hardcoded() {
        assertTrue(StringAttributeFilter.isHardcodedLiteral("Hello"));
        assertFalse(StringAttributeFilter.isHardcodedLiteral("@string/x"));
        assertFalse(StringAttributeFilter.isHardcodedLiteral("?attr/x"));
        assertFalse(StringAttributeFilter.isHardcodedLiteral("  "));
    }
}

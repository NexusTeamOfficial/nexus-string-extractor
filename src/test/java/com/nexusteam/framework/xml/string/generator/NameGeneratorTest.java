package com.nexusteam.framework.xml.string.generator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameGeneratorTest {

    @Test
    void sequentialLetters() {
        NameGenerator gen = new NameGenerator();
        assertEquals("a", gen.next());
        assertEquals("b", gen.next());
    }

    @Test
    void afterZComesAa() {
        NameGenerator gen = new NameGenerator(25);
        assertEquals("z", gen.next());
        assertEquals("aa", gen.next());
    }

    @Test
    void toNameMapping() {
        assertEquals("a", NameGenerator.toName(0));
        assertEquals("z", NameGenerator.toName(25));
        assertEquals("aa", NameGenerator.toName(26));
    }

    @Test
    void prefix() {
        NameGenerator gen = new NameGenerator(0, "str_");
        assertEquals("str_a", gen.next());
    }
}

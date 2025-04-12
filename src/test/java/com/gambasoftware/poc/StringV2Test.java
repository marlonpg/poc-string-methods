package com.gambasoftware.poc;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringV2Test {

    @Test
    void testLength() {
        StringV2 str = new StringV2("hello");
        assertEquals(5, str.length());
    }

    @Test
    void testToArray() {
        StringV2 str = new StringV2("abc");
        char[] expected = {'a', 'b', 'c'};
        assertArrayEquals(expected, str.toArray());
    }

    @Test
    void testForEach() {
        StringV2 str = new StringV2("xyz");
        List<Character> collected = new ArrayList<>();

        str.forEach(collected::add);

        assertEquals(List.of('x', 'y', 'z'), collected);
    }

    @Test
    void testReverse() {
        StringV2 str = new StringV2("world");
        char[] reversed = str.reverse();

        assertArrayEquals(new char[]{'d', 'l', 'r', 'o', 'w'}, reversed);
    }
}

package com.gambasoftware.poc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringV2Test {

    @Test
    void testConstructors() {
        // Test constructor with char array
        char[] chars = {'h', 'e', 'l', 'l', 'o'};
        StringV2 strFromChars = new StringV2(chars);
        assertArrayEquals(chars, strFromChars.toArray());

        // Test constructor with String
        StringV2 strFromString = new StringV2("hello");
        assertArrayEquals(chars, strFromString.toArray());
        
        // Test that modifying original array doesn't affect StringV2
        chars[0] = 'x';
        assertNotEquals('x', strFromChars.toArray()[0]);
    }

    @Test
    void testLength() {
        assertEquals(0, new StringV2("").length());
        assertEquals(5, new StringV2("hello").length());
        assertEquals(1, new StringV2("a").length());
    }

    @Test
    void testToArray() {
        StringV2 str = new StringV2("abc");
        char[] expected = {'a', 'b', 'c'};
        assertArrayEquals(expected, str.toArray());
        
        // Test that returned array is a copy
        char[] array = str.toArray();
        array[0] = 'x';
        assertNotEquals('x', str.toArray()[0]);
    }

    @Test
    void testForEach() {
        StringV2 str = new StringV2("xyz");
        List<Character> collected = new ArrayList<>();
        str.forEach(collected::add);
        assertEquals(List.of('x', 'y', 'z'), collected);
    }

    @Test
    void testIterator() {
        StringV2 str = new StringV2("abc");
        var iterator = str.iterator();
        
        assertTrue(iterator.hasNext());
        assertEquals('a', iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals('b', iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals('c', iterator.next());
        assertFalse(iterator.hasNext());

        assertThrows(ArrayIndexOutOfBoundsException.class, iterator::next);
    }

    @Test
    void testReverse() {
        StringV2 str = new StringV2("world");
        char[] reversed = str.reverse();
        assertArrayEquals(new char[]{'d', 'l', 'r', 'o', 'w'}, reversed);
        
        // Test empty string
        assertArrayEquals(new char[]{}, new StringV2("").reverse());
        
        // Test single character
        assertArrayEquals(new char[]{'a'}, new StringV2("a").reverse());
    }

    @ParameterizedTest
    @CsvSource({
        "hello, 0, h",
        "hello, 4, o",
        "hello, 2, l"
    })
    void testCharAtValid(String input, int index, char expected) {
        StringV2 str = new StringV2(input);
        assertEquals(expected, str.charAt(index));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 5, 100})
    void testCharAtInvalid(int index) {
        StringV2 str = new StringV2("hello");
        assertThrows(IndexOutOfBoundsException.class, () -> str.charAt(index));
    }

    @Test
    void testEmptyString() {
        StringV2 empty = new StringV2("");
        assertEquals(0, empty.length());
        assertArrayEquals(new char[]{}, empty.toArray());
        assertArrayEquals(new char[]{}, empty.reverse());
        assertFalse(empty.iterator().hasNext());
    }
}

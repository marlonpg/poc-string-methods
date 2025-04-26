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

    @Test
    void testIsEmpty_withEmptyString() {
        StringV2 empty = new StringV2("");
        assertTrue(empty.isEmpty(), "Expected isEmpty() to return true for empty string");
    }

    @Test
    void testIsEmpty_withNonEmptyString() {
        StringV2 nonEmpty = new StringV2("hello");
        assertFalse(nonEmpty.isEmpty(), "Expected isEmpty() to return false for non-empty string");
    }

    @Test
    void testIsEmpty_withNull() {
        StringV2 withNull = new StringV2(new char[]{});
        assertTrue(withNull.isEmpty(), "Expected isEmpty() to return true for empty char array");
    }

    @Test
    void testReplaceACharacterThatExists() {
        StringV2 original = new StringV2("banana");
        StringV2 replaced = original.replace('a', 'o');
        assertArrayEquals(new char[]{'b', 'o', 'n', 'o', 'n', 'o'}, replaced.toArray());
    }

    @Test
    void testReplaceACharacterThatDoesNotExist() {
        StringV2 original = new StringV2("banana");
        StringV2 unchanged = original.replace('x', 'y');
        assertArrayEquals(new char[]{'b', 'a', 'n', 'a', 'n', 'a'}, unchanged.toArray());
    }

    @Test
    void testReplaceEmptyStringV2() {
        StringV2 empty = new StringV2("");
        StringV2 stillEmpty = empty.replace('a', 'b');
        assertArrayEquals(new char[]{}, stillEmpty.toArray());
    }

    @Test
    void testSubstringHappyPath() {
        StringV2 original = new StringV2("abcdef");

        StringV2 sub = original.substring(1, 4);
        assertArrayEquals(new char[] {'b', 'c', 'd'}, sub.toArray());
    }

    @Test
    void testSubstringEqualRanges() {
        StringV2 original = new StringV2("abcdef");

        StringV2 empty = original.substring(2, 2);
        assertArrayEquals(new char[] {}, empty.toArray());
    }


    @Test
    void testSubstringInitialPosGreaterThanZero() {
        StringV2 str = new StringV2("abc");
        assertThrows(IndexOutOfBoundsException.class, () -> str.substring(-1, 2));
    }

    @Test
    void testSubstringFinalPosGreaterThanLength() {
        StringV2 str = new StringV2("abc");
        assertThrows(IndexOutOfBoundsException.class, () -> str.substring(1, 5));
    }

    @Test
    void testSubstringInitialPosGreaterThanFinalPos() {
        StringV2 str = new StringV2("abc");
        assertThrows(IndexOutOfBoundsException.class, () -> str.substring(2, 1));
    }

    @Test
    void testTrimWithLeadingAndTrailingSpaces() {
        StringV2 str = new StringV2("   hello world   ");
        StringV2 trimmed = str.trim();
        assertArrayEquals("hello world".toCharArray(), trimmed.toArray());
    }

    @Test
    void testTrimWithOnlyLeadingSpaces() {
        StringV2 str = new StringV2("   leading");
        StringV2 trimmed = str.trim();
        assertArrayEquals("leading".toCharArray(), trimmed.toArray());
    }

    @Test
    void testTrimWithOnlyTrailingSpaces() {
        StringV2 str = new StringV2("trailing   ");
        StringV2 trimmed = str.trim();
        assertArrayEquals("trailing".toCharArray(), trimmed.toArray());
    }

    @Test
    void testTrimWithNoSpaces() {
        StringV2 str = new StringV2("clean");
        StringV2 trimmed = str.trim();
        assertArrayEquals("clean".toCharArray(), trimmed.toArray());
    }

    @Test
    void testTrimAllSpaces() {
        StringV2 str = new StringV2("     ");
        StringV2 trimmed = str.trim();
        assertArrayEquals(new char[] {}, trimmed.toArray());
    }

    @Test
    void testTrimEmptyString() {
        StringV2 str = new StringV2("");
        StringV2 trimmed = str.trim();
        System.out.println(trimmed);
        assertArrayEquals(new char[] {}, trimmed.toArray());
    }

    @Test
    void testIndexOfCharacterFound() {
        StringV2 str = new StringV2("banana");

        assertEquals(1, str.indexOf('a'), "The first occurrence of 'a' should be at index 1");
        assertEquals(2, str.indexOf('n'), "The first occurrence of 'n' should be at index 2");
    }

    @Test
    void testIndexOfCharacterNotFound() {
        StringV2 str = new StringV2("banana");

        assertEquals(-1, str.indexOf('z'), "'z' should not be found in the string");
    }

    @Test
    void testIndexOfEmptyString() {
        StringV2 empty = new StringV2("");

        assertEquals(-1, empty.indexOf('a'), "An empty string should return -1 for any character search");
    }

    @Test
    void testHighUnicodeCharacterHandling() {
        // U+1F600 is 😀 (grinning face emoji)
        String emoji = "\uD83D\uDE00"; // surrogate pair representation of U+1F600
        StringV2 str = new StringV2(emoji);

        // It will be length 2 because of the surrogate pair
        assertEquals(2, str.length());

        // Characters aren't handled as full code points
        assertEquals('\uD83D', str.toArray()[0]); // high surrogate
        assertEquals('\uDE00', str.toArray()[1]); // low surrogate

        // Should fail: emoji isn't equal to a single char
        assertNotEquals('😀', str.toArray()[0]); // Can't represent the whole emoji with one char
    }
}

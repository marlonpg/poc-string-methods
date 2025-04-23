package com.gambasoftware.poc;

import java.util.Iterator;

public class StringV2 implements Iterable<Character> {

    //todo maybe we should have it in int? to cover all 16 bit chars?
    char[] value;

    public StringV2(char[] value) {
        this.value = value.clone();
    }

    public StringV2(String value) {
        this.value = value.toCharArray();
    }

    public char[] toArray() {
        return value.clone();
    }

    public int length() {
        return value.length;
    }

    public char[] reverse() {
        char[] reversed = new char[value.length];
        for (int i = 0; i < value.length; i++) {
            reversed[i] = value[value.length - 1 - i];
        }
        return reversed;
    }

    @Override
    public Iterator<Character> iterator() {
        return new Iterator<>() {
            private int index = 0;

            public boolean hasNext() {
                return index < value.length;
            }

            public Character next() {
                return value[index++];
            }
        };
    }

    public char charAt(int index) {
        if (index < 0 || index >= value.length) {
            throw new IndexOutOfBoundsException("StringV2 index out of range: " + index);
        }
        return value[index];
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        StringV2 other = (StringV2) obj;
        if (value.length != other.value.length) return false;
        for (int i = 0; i < value.length; i++) {
            if (value[i] != other.value[i]) return false;
        }
        return true;
    }

    public boolean isEmpty() {
        return value == null || value.length == 0;
    }

    public StringV2 replace(char oldValue, char newValue) {
        char[] result = value.clone();
        for (int i = 0; i < value.length; i++) {
            if (result[i] == oldValue) {
                result[i] = newValue;
            }
        }
        return new StringV2(result);
    }

    public StringV2 substring(int initialPos, int finalPos) {
        if (initialPos < 0 || initialPos > finalPos || finalPos >= value.length) {
            throw new IndexOutOfBoundsException("initialPos or finalPos are out of range: " + 0 + " to " + value.length);
        }
        char[] result = new char[finalPos - initialPos];
        int resultPos = 0;
        for (int i = initialPos; i < finalPos; i++, resultPos++) {
            result[resultPos] = value[i];
        }
        return new StringV2(result);
    }

    public StringV2 trim() {
        char[] result = value.clone();
        boolean startBlank = true;
        int countStartBlank = 0;
        int countEndBlank = 0;
        boolean endBlank = true;
        for (int i = 0, j = value.length - 1; i < value.length; i++, j--) {
            if (startBlank && result[i] == ' ') {
                countStartBlank++;
            } else {
                startBlank = false;
            }
            if (endBlank && result[j] == ' ') {
                countEndBlank++;
            } else {
                endBlank = false;
            }
        }
        if(countStartBlank == value.length){
            countEndBlank = 0;
        }
        int newArraySize = value.length - (countStartBlank + countEndBlank);
        char[] trimResult = new char[newArraySize];
        for (int i = countStartBlank, j=0; i < value.length && j < trimResult.length ; i++,j++) {
            trimResult[j] = value[i];
        }
        return new StringV2(trimResult);
    }

    public int indexOf(char target) {
        for (int i = 0; i < value.length; i++) {
            if (value[i] == target) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return new String(value);
    }
}

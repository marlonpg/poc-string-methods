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

    public int length(){
        return value.length;
    }

    public char[] reverse(){
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

    public boolean isEmpty(){
        return value == null || value.length == 0;
    }
}

package com.gambasoftware.poc;

import java.util.function.Consumer;

public class StringV2 {

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

    public void forEach(Consumer<Character> operation) {
        for (char c : value) {
            operation.accept(c);
        }
    }
}

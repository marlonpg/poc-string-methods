package com.gambasoftware.poc;

public class Main {
    public static void main(String[] args) {
        String string = "";

        StringV2 stringV2 = new StringV2(new char[]{'v','a','l','u','e'});
        StringV2 stringV2FromString = new StringV2("value");

        System.out.println("Length: " + stringV2.length());
        System.out.println("toArray: " + stringV2.toArray());
        System.out.print("forEach: ");
        stringV2.forEach(System.out::print);
        System.out.println(" ");
    }
}

package com.macv.tests.util;

public class StringUtil {
    public static String repeat(String str, int times) throws IllegalAccessException {

        if (times < 0){
            throw new IllegalArgumentException("Times cannot be negative");
        }

        String result = "";

        for (int i = 0; i < times; i++) {
            result += str;
        }

        return result;
    }
}

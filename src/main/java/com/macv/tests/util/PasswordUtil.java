package com.macv.tests.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordUtil {
    public enum SecurityLevel {
        WEAK, MEDIUM, STRONG;
    }

    public static SecurityLevel assessPassword(String password){
        if(password.length() <= 8 ){
            return SecurityLevel.WEAK;
        }

        if (password.matches("[a-zA-Z]")){
            return SecurityLevel.WEAK;
        }

        String regexNumbersLettersSymbols = "(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9])";
        Pattern pattern2 = Pattern.compile(regexNumbersLettersSymbols);
        Matcher matcher2 = pattern2.matcher(password);

        if (matcher2.find()){
            return SecurityLevel.STRONG;
        }

        String regexNumbersLetters = "(?=.*[a-zA-Z])(?=.*[0-9])";
        Pattern pattern1 = Pattern.compile(regexNumbersLetters);
        Matcher matcher1 = pattern1.matcher(password);

        if (matcher1.find()){
            return SecurityLevel.MEDIUM;
        }

        return null;
    }
}

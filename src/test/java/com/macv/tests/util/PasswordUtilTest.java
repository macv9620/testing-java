package com.macv.tests.util;

import org.junit.Test;

import static com.macv.tests.util.PasswordUtil.*;
import static org.junit.Assert.*;

public class PasswordUtilTest {
    @Test
    public void weakWhenHasLessThan8Letters() {
        assertEquals(SecurityLevel.WEAK, assessPassword("hola"));
    }

    @Test
    public void weakWhenHasOnlyLetters() {
        assertEquals(SecurityLevel.WEAK, assessPassword("holass"));
    }

    @Test
    public void mediumWhenHasLettersAndNumbers() {
        assertEquals(SecurityLevel.MEDIUM, assessPassword("ho77fdsffdf"));
    }

    @Test
    public void strongWhenHasLettersNumbersAndSymbols() {
        assertEquals(SecurityLevel.STRONG, assessPassword("ho7/)((=?¡7fdsffdf"));
    }
}
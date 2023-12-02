package com.macv.tests.util;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilTest {

    @Test
    public void testRepeatTwoTimes() throws IllegalAccessException {
        Assert.assertEquals("mateomateo", StringUtil.repeat("mateo", 2));
    }

    @Test
    public void testRepeatOneTime() throws IllegalAccessException {
        Assert.assertEquals("mateo", StringUtil.repeat("mateo", 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRepeatNegativeTimes() throws IllegalAccessException {
        StringUtil.repeat("Holi", -5);
    }

}
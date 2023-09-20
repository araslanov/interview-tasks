package com.wizeek.reid;

import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void test1() {
        Assert.assertEquals("23571", Solution.solution(0));
    }

    @Test
    public void test2() {
        Assert.assertEquals("71113", Solution.solution(3));
    }
}

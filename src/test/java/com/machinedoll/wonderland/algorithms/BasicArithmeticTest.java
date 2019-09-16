package com.machinedoll.wonderland.algorithms;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BasicArithmeticTest {
    BasicArithmetic basicObj = new BasicArithmetic();

    @Before
    public void setUp() {
        System.out.println("--------------------");
        long startTime = System.currentTimeMillis();
    }

    public void tearDown() {
        System.out.println("--------------------");

    }

    @Test
    public void simpleLoopFib() {
        try {
            basicObj.simpleLoopFib(100000);
        } catch (StackOverflowError e) {
            System.out.println("Stackover flow error");
        } catch (Exception e) {
            System.out.println("Unkown error occured");
        }
        long interval = System.currentTimeMillis() - startTime;
        System.out.println(String.format("Time passed = %s ms", interval));
    }

    @Test
    public void simpleRecFib() {
        long startTime = System.currentTimeMillis();
        try {
            basicObj.simpleRecFib(10000);
        } catch (StackOverflowError e) {
            System.out.println("Stackover flow error");
        } catch (Exception e) {
            System.out.println("Unkown error occured");
        }
        long interval = System.currentTimeMillis() - startTime;
        System.out.println(String.format("Time passed = %s ms", interval));
    }

    @Test
    public void getAsAProductOfItsPrimeFactor() {
        long startTime = System.currentTimeMillis();
        List<Long> res = basicObj.getAsAProductOfItsPrimeFactor(10L);
        long interval = System.currentTimeMillis() - startTime;
        res.stream().forEach(elem -> System.out.println(elem));
        System.out.println(String.format("Time passed = %s ms", interval));
    }
}
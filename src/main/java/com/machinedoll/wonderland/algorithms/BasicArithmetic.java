package com.machinedoll.wonderland.algorithms;

import com.machinedoll.wonderland.lib.AlgorithmRunner;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class BasicArithmetic implements AlgorithmRunner {
    private Config conf;

    public void run() {

    }

    public BasicArithmetic() {
        this.conf = ConfigFactory.load();
    }

    public BigInteger simpleLoopFib(int num) {
        BigInteger arr[] = new BigInteger[num];
        arr[0] = BigInteger.valueOf(0);
        arr[1] = BigInteger.valueOf(1);
        for (int curr = 2; curr < num; curr++) {
            arr[curr] = arr[curr - 1].add(arr[curr - 2]);
        }

        return arr[num - 1];
    }

    public BigInteger simpleRecFib(int num) {
        if (num == 0) return BigInteger.valueOf(0);
        else if (num == 1) return BigInteger.valueOf(1);
        else return simpleRecFib(num - 1).add(simpleRecFib(num - 2));
    }

    public List<Long> getAsAProductOfItsPrimeFactor(Long number) {
        // Given a number N, express it as a product of its prime factors
        List<Long> res = new ArrayList<>();
        return res;
    }


}

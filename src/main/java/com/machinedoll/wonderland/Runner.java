package com.machinedoll.wonderland;

import com.machinedoll.wonderland.algorithms.BasicArithmetic;
import com.machinedoll.wonderland.factory.AlgorithmFactory;
import com.machinedoll.wonderland.features.java.ConcurrencyCyclicBarrierExample;
import com.machinedoll.wonderland.lib.AlgorithmRunner;

public class Runner {
    public static void prepareRuntimeEnvironment() {
        AlgorithmFactory.register(BasicArithmetic.class);
        AlgorithmFactory.register(ConcurrencyCyclicBarrierExample.class);
    }

    public static void main(String[] args) throws Exception {
        prepareRuntimeEnvironment();
        AlgorithmRunner runner = AlgorithmFactory.getAlgorithmRunner("BasicArithmetic");
        runner.run();
    }
}

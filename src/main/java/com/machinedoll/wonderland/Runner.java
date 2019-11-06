package com.machinedoll.wonderland;

import com.machinedoll.wonderland.algorithms.BasicArithmetic;
import com.machinedoll.wonderland.factory.AlgorithmFactory;
import com.machinedoll.wonderland.features.java.ConcurrencyCountDownLatch;
import com.machinedoll.wonderland.features.java.ConcurrencyCyclicBarrier;
import com.machinedoll.wonderland.features.java.ConcurrencyPhaser;
import com.machinedoll.wonderland.lib.AlgorithmRunner;

public class Runner {
  public static void prepareRuntimeEnvironment() {
    AlgorithmFactory.register(BasicArithmetic.class);
    AlgorithmFactory.register(ConcurrencyCyclicBarrier.class);
    AlgorithmFactory.register(ConcurrencyCountDownLatch.class);
    AlgorithmFactory.register(ConcurrencyPhaser.class);
  }

  public static void main(String[] args) throws Exception {
    prepareRuntimeEnvironment();
    AlgorithmRunner runner = AlgorithmFactory.getAlgorithmRunner("ConcurrencyPhaser");
    runner.run();
  }
}

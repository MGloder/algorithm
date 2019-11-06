package com.machinedoll.wonderland.features.java;

import com.machinedoll.wonderland.lib.AlgorithmRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*
  Java Concurrency - Synchronizers

  The java.util.concurrent package contain serveral classes:
    CyclicBarrier
    Phaser
    CountDownLatch
    Exchanger
    Semaphore
    SynchronousQueue
 */
public class ConcurrencyPhaser implements AlgorithmRunner {
  List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());

  @Override
  public void run() {

  }

  private void runSimulation() throws InterruptedException {

  }
}

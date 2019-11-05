package com.machinedoll.wonderland.features.java;

import com.machinedoll.wonderland.lib.AlgorithmRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;


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
public class ConcurrencyCyclicBarrierExample implements AlgorithmRunner {
  private CyclicBarrier cyclicBarrier;
  private List<List<Integer>> partialResults = Collections.synchronizedList(new ArrayList<>());
  private Random random = new Random();
  private int NUM_PARTIAL_RESULTS;
  private int NUM_WORKERS;

  @Override
  public void run() {
    cyclicBarrier = new CyclicBarrier(1);

  }

  class AggregatorThread implements Runnable {
    @Override
    public void run() {
      String thisThreadName = Thread.currentThread().getName();

      System.out.println(thisThreadName + ": Computing sum of" + NUM_WORKERS +
          "workers, having" + NUM_PARTIAL_RESULTS + " results each.");

      int sum = 0;

      partialResults.stream().map(threadResult -> {
        System.out.println("Adding ");
        threadResult.stream().reduce(sum, (partialResult) ->  sum + partialResult);
        return Integer.valueOf(0);
      });

    }
  }


  class NumberCruncherThread implements Runnable {
    @Override
    public void run() {
      String thisThreadName = Thread.currentThread().getName();
      List<Integer> partialResult = new ArrayList<>();
      IntStream.range(1, NUM_PARTIAL_RESULTS).map(action -> {
        Integer num = random.nextInt();
        partialResult.add(num);
        System.out.println(thisThreadName + ": Crunching some numbers! Final result - " + num);
        return num;
      });

      partialResults.add(partialResult);

      try {
        System.out.println(thisThreadName + " waiting for others to reach barrier.");
        cyclicBarrier.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      } catch (BrokenBarrierException e) {
        e.printStackTrace();
      }


    }
  }
}

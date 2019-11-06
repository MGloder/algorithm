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
public class ConcurrencyCyclicBarrier implements AlgorithmRunner {
  private CyclicBarrier cyclicBarrier;
  private List<List<Integer>> partialResults = Collections.synchronizedList(new ArrayList<>());
  private Random random = new Random();
  private int NUM_PARTIAL_RESULTS;
  private int NUM_WORKERS;

  @Override
  public void run() {
    runSimulation(1000, 200);
  }

  private void runSimulation(int numWorkers, int numberOfPartialResults) {
    NUM_PARTIAL_RESULTS = numberOfPartialResults;
    NUM_WORKERS = numWorkers;

    cyclicBarrier = new CyclicBarrier(NUM_WORKERS, new AggregatorThread());

    System.out.println("Spawning " + NUM_WORKERS + " worker thread to compute " + NUM_PARTIAL_RESULTS + " partial results each ");

    IntStream.range(0, NUM_WORKERS).forEach(i -> {
      Thread worker = new Thread(new NumberCruncherThread());
      worker.setName("Thread " + i);
      worker.start();
    });
  }

  class NumberCruncherThread implements Runnable {
    @Override
    public void run() {
      String thisThreadName = Thread.currentThread().getName();
      List<Integer> partialResult = new ArrayList<>();
      IntStream.range(0, NUM_PARTIAL_RESULTS).forEach(action -> {
        Integer num = random.nextInt(10);
        partialResult.add(num);
        System.out.println(thisThreadName + ": Crunching some numbers! Final result - " + num);
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

  class AggregatorThread implements Runnable {
    @Override
    public void run() {
      String thisThreadName = Thread.currentThread().getName();

      System.out.println(thisThreadName + ": Computing sum of " + NUM_WORKERS +
          " workers, having " + NUM_PARTIAL_RESULTS + " results each.");

      int sum = partialResults.stream().map(partialResult -> {
        System.out.println("Adding " + partialResult);
        return partialResult.stream().reduce(0, (x, y) -> x + y);
      }).reduce(0, Integer::sum);

      System.out.println(thisThreadName + ": Final result = " + sum);
    }
  }

}

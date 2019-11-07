package com.machinedoll.wonderland.features.java.concurrency;

import com.machinedoll.wonderland.lib.AlgorithmRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


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
public class ConcurrencyCountDownLatch implements AlgorithmRunner {
  List<String> outputScraper = Collections.synchronizedList(new ArrayList<>());
  CountDownLatch countDownLatch = new CountDownLatch(5);

  @Override
  public void run() {
    try {
      runSimulation();
      outputScraper.forEach(System.out::println);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private void runSimulation() throws InterruptedException {
    List<Thread> workers = Stream
        .generate(() -> new Thread(new Worker(outputScraper, countDownLatch)))
        .limit(5)
        .collect(toList());
    workers.forEach(Thread::start);
    countDownLatch.await();
    outputScraper.add("Latch Released");
  }

  class Worker implements Runnable {
    private List<String> outputScraper;
    private CountDownLatch countDownLatch;

    public Worker(List<String> outputScraper, CountDownLatch countDownLatch) {
      this.outputScraper = outputScraper;
      this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
      System.out.println("Sleeping ... ");
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      outputScraper.add("Count Down");
      countDownLatch.countDown();
    }
  }
}

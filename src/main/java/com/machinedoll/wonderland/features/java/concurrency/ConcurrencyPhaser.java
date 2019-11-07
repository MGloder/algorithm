package com.machinedoll.wonderland.features.java.concurrency;

import com.machinedoll.wonderland.lib.AlgorithmRunner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

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

  @Override
  public void run() {
    ExecutorService executorService = Executors.newCachedThreadPool();
    Phaser ph = new Phaser(1) {
      protected boolean onAdvance(int phase, int parties) {
        System.out.println("phase " + phase + " finished");
        return false;
      }
    };
    System.out.println(ph.getPhase());

    executorService.submit(new LongRunningAction("thread-1", ph));
    executorService.submit(new LongRunningAction("thread-2", ph));
    executorService.submit(new LongRunningAction("thread-3", ph));

    ph.arriveAndAwaitAdvance();

    executorService.submit(new LongRunningAction("thread-4", ph));
    executorService.submit(new LongRunningAction("thread-5", ph));
    ph.arriveAndAwaitAdvance();

    ph.arriveAndDeregister();
  }

  class LongRunningAction implements Runnable {
    private String threadName;
    private Phaser ph;

    LongRunningAction(String threadName, Phaser ph) {
      this.threadName = threadName;
      this.ph = ph;
      ph.register();
    }

    @Override
    public void run() {
      ph.arriveAndAwaitAdvance();
      try {
        System.out.println("This thread name " + threadName);
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("arrived");
      ph.arriveAndDeregister();
    }
  }
}

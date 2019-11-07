package com.machinedoll.wonderland.features.java.concurrency;

import com.machinedoll.wonderland.lib.AlgorithmRunner;

import java.util.concurrent.*;

public class ConcurrencySynchronousQueue implements AlgorithmRunner {
  private SynchronousQueue<Integer> queue = new SynchronousQueue<>();

  @Override
  public void run() throws InterruptedException {
    simulation();
  }

  private void simulation() throws InterruptedException {
    Runnable producer = () -> {
      Integer producedElement = ThreadLocalRandom.current().nextInt();
      try {
        queue.put(producedElement);
        System.out.println("Produce result: " + producedElement);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    };

    Runnable consumer = () -> {
      try {
        Integer out = queue.take();
        System.out.println("Consume result: " + out);
        System.out.println("Current size: " + queue.size());
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    };

    ExecutorService executorService = Executors.newFixedThreadPool(2);
    executorService.execute(producer);
    executorService.execute(consumer);
    executorService.awaitTermination(5, TimeUnit.MILLISECONDS);
    executorService.shutdown();
  }

}

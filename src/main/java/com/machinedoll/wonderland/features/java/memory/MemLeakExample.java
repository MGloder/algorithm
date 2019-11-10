package com.machinedoll.wonderland.features.java.memory;

import com.machinedoll.wonderland.lib.AlgorithmRunner;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class MemLeakExample implements AlgorithmRunner {

  @Override
  public void run() throws InterruptedException {
    TaskList taskList = new TaskList();
    final TaskCreator taskCreator = new TaskCreator(taskList);

    new Thread(new Runnable() {
      @Override
      public void run() {
        IntStream.range(0, 10000).forEach(taskCreator.createTask());
      }
    }).start();
  }

  private static class TaskList {
    private Deque<Task> tasks = new ArrayDeque<Task>();
    public void addTask(Task task) {
      tasks.add(task);
      tasks.peek().execute();
    }

    private static class Task {
      public Task() {
      }

      public void execute() {
        System.out.println("execute task " + this.getClass().getSimpleName());
      }
    }
  }


  private class TaskCreator {
    private TaskList taskList;
    public TaskCreator(TaskList taskList) {
      this.taskList = taskList;
    }

    public IntConsumer createTask() {
      TaskList.Task task = new TaskList.Task();
      taskList.addTask(task);
      IntConsumer consumer = t -> new TaskList.Task();
      return consumer;
    }
  }
}

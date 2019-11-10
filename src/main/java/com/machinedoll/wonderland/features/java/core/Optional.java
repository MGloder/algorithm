package com.machinedoll.wonderland.features.java.core;

import com.machinedoll.wonderland.lib.AlgorithmRunner;

public class Optional<I extends Number> implements AlgorithmRunner {
  @Override
  public void run() {
    simulation();
  }

  private void simulation() {
    System.out.print("Get Non Empty Integer: ");
    java.util.Optional<Object> nonEmptyInteger = getNonEmptyInteger();
    System.out.println(nonEmptyInteger.get());

    System.out.print("Print 0 if not present: ");
    java.util.Optional<Object> emptyInteger = getEmptyInteger();
    System.out.println(emptyInteger.orElse(Integer.valueOf(0)));
  }

  private java.util.Optional<Object> getNonEmptyInteger() {
    return java.util.Optional.of(Integer.valueOf(1));
  }

  private java.util.Optional<Object> getEmptyInteger() {
    return java.util.Optional.empty();
  }

}

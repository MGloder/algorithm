package com.machinedoll.wonderland;

import java.util.Arrays;
import java.util.List;

public class Tester {
  public static void main(String[] args) throws Exception {
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
    int sum = numbers
        .stream()
        .reduce(0, Integer::sum);
    System.out.println(sum);
  }
}

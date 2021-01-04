package com.machinedoll.wonderland.factory;

import com.machinedoll.wonderland.lib.AlgorithmRunner;

import java.util.HashMap;
import java.util.Map;

public class AlgorithmFactory {
  private static final AlgorithmFactory instance = new AlgorithmFactory();
  private static Map<String, Class> algorithmMap = new HashMap<>();

  private AlgorithmFactory() {
  }

  public static AlgorithmFactory getInstance() {
    return instance;
  }

  public static void register(Class<? extends AlgorithmRunner> f) {
    algorithmMap.put(f.getSimpleName(), f);
  }

  public static AlgorithmRunner getAlgorithmRunner(String algorithm) throws Exception {
    Class<? extends AlgorithmRunner> clazz = algorithmMap.get(algorithm);
    if (clazz == null) {
      throw new Exception("Error");
    }
    return clazz.newInstance();
  }
}

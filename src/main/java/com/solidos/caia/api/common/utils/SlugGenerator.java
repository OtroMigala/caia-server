package com.solidos.caia.api.common.utils;

public class SlugGenerator {
  public static String generate(String name) {
    return name.toLowerCase().replace(" ", "-");
  }
}

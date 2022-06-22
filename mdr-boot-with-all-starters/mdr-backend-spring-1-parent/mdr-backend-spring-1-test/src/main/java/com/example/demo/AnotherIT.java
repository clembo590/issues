package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class AnotherIT extends BaseIT {

  @Test
  public void myTest() {
    log.info("also testing here");
  }
}

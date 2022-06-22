package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ParallelExecutionExceptionTest {

  public static final String fake2 = "oi";

  @Test
  public void whatever() {
    log.info("here seems ok too");
  }
}

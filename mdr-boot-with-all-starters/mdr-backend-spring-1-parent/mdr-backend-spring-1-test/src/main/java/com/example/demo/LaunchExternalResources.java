package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

@Slf4j
public class LaunchExternalResources implements BeforeAllCallback {

  static {
    log.info(ParallelExecutionExceptionTest.fake2);
    if (true) {
      log.info("should be ok");
    }
  }

  @Override
  public void beforeAll(ExtensionContext extensionContext) {
    log.info("nothing to do here");
  }
}

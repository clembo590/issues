package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@ActiveProfiles("test")
@ExtendWith(LaunchExternalResources.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
// By default, both JUnit 4 and 5 creates
// a new instance of the test class before running each test method
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseIT {

  @LocalServerPort protected int port;

  public BaseIT() {
    super();
  }

  @Test
  public void firsttestCopy() {
    log.info("it it indeeded testing");
  }
}

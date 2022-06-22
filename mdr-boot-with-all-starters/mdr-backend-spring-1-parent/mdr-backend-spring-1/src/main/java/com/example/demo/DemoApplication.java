package com.example.demo;

import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DemoApplication {

  public static void main(String[] args) {
    createSpringApplication().run(args);
  }

  public static SpringApplication createSpringApplication() {
    return new SpringApplication(DemoApplication.class);
  }

  @PostConstruct
  public void sayHello() {
    log.info("say hello 10");
  }
}

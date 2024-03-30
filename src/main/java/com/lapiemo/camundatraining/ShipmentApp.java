package com.lapiemo.camundatraining;

import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableProcessApplication
public class ShipmentApp {

  public static void main(String... args) {
    SpringApplication.run(ShipmentApp.class, args);
  }

}
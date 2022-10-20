package com.example.baggagecalc2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class BaggageCalc2Application {

    public static void main(String[] args) {
        SpringApplication.run(BaggageCalc2Application.class, args);
    }

}

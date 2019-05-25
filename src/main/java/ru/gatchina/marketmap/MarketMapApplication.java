package ru.gatchina.marketmap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "ru.gatchina.marketmap.controller")
public class MarketMapApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketMapApplication.class, args);
    }

}

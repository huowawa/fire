package com.soft.fire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableTransactionManagement(mode = AdviceMode.PROXY)
public class FireApplication {

    public static void main(String[] args) {

        SpringApplication.run(FireApplication.class, args);
    }

}


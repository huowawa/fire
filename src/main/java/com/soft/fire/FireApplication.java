package com.soft.fire;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.soft.fire.platform.*.mapper")
public class FireApplication {

    public static void main(String[] args) {
        SpringApplication.run(FireApplication.class, args);
    }
}


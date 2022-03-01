package com.yoonho.piclog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PiclogApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiclogApplication.class, args);
    }

}

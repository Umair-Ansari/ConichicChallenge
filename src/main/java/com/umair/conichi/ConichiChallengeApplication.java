package com.umair.conichi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableScheduling
@EnableSwagger2
@SpringBootApplication
public class ConichiChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConichiChallengeApplication.class, args);
    }

}

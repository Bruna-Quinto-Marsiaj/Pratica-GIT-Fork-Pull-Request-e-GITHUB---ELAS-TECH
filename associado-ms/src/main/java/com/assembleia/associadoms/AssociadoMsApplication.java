package com.assembleia.associadoms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AssociadoMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssociadoMsApplication.class, args);
    }

}

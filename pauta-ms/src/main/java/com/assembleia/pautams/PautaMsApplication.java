package com.assembleia.pautams;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableRabbit
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class PautaMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PautaMsApplication.class, args);
    }

}

package com.assembleia.eurekaserverms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerMsApplication.class, args);
	}

}

package com.assembleia.pautams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@RibbonClient(name = "associado-ms")
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class PautaMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PautaMsApplication.class, args);
	}

}

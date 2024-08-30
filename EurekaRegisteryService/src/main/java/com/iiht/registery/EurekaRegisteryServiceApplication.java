package com.iiht.registery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaRegisteryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaRegisteryServiceApplication.class, args);
	}

}

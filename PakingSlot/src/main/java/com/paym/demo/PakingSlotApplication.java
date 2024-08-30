package com.paym.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class PakingSlotApplication {

	public static void main(String[] args) {
		SpringApplication.run(PakingSlotApplication.class, args);
	}

}

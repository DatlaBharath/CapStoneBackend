package com.livingsync.annoucements;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class AnouncementsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnouncementsApplication.class, args);
	}
	@Configuration
	public class RestTemplateConfig {

	    @Bean
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }
	}
	


}

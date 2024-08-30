package com.livingsync.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;

@Configuration
public class AwsConfig {

    @Bean
    public SnsClient snsClient() {
        return SnsClient.builder()
                .region(Region.of("ap-south-1"))  // Replace with your AWS region
                .build();
    }
}
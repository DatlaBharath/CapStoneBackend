package com.livingsync.user.service;

import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.SubscribeRequest;
import software.amazon.awssdk.services.sns.model.SubscribeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SnsService {

    @Autowired
    private SnsClient snsClient;

    public String subscribeEmailToTopic(String topicArn, String email) {
        SubscribeRequest request = SubscribeRequest.builder()
                .topicArn(topicArn)
                .protocol("email")
                .endpoint(email)
                .build();

        SubscribeResponse result = snsClient.subscribe(request);
        return result.subscriptionArn();
    }
    public String subscribePhoneToTopic(String topicArn,String phone) {
    	SubscribeRequest request = SubscribeRequest.builder()
                .topicArn(topicArn)
                .protocol("sns")
                .endpoint(phone)
                .build();
    	SubscribeResponse result = snsClient.subscribe(request);
        return result.subscriptionArn();
    }
}
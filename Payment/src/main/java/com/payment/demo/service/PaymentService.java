package com.payment.demo.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.payment.demo.client.RazorpayClient;
import com.payment.demo.client.UserClient;
import com.payment.demo.dto.RazorpayRequest;
import com.payment.demo.dto.RazorpayResponse;
import com.payment.demo.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payment.demo.entity.PaymentHistory;
import com.payment.demo.entity.PaymentRequest;
import com.payment.demo.repository.PaymentHistoryRepository;
import com.payment.demo.repository.PaymentRequestRepository;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;
import software.amazon.awssdk.services.sns.model.PublishResponse;

@Service
public class PaymentService {

    @Autowired
    private PaymentRequestRepository paymentRequestRepository;

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

    @Autowired
    private RazorpayClient razorpayClient;

    @Autowired
    private UserClient userClient;

    @Autowired
    private SnsClient snsClient;

    public String publishMessage(String message) {
        String topicArn = "arn:aws:sns:ap-south-1:014498630957:notifications"; // Replace with your SNS Topic ARN
        PublishRequest request = PublishRequest.builder()
                .message(message)
                .topicArn(topicArn)
                .build();

        PublishResponse result = snsClient.publish(request);
        return result.messageId();
    }
    public RazorpayResponse createPaymentLink(int amount,  String description, String email) {
        UserResponse usr = userClient.getUserByEmail(email).get();
        String phoneNumber = usr.getMobile();
        String name = usr.getFullName();
        RazorpayRequest request = new RazorpayRequest();
        request.setAmount(amount*100);
        request.setExpire_by(Instant.now().getEpochSecond() + 45 * 60); // Expiry time is 45 minutes from now
        request.setReference_id(generateUniqueReferenceId());

        RazorpayRequest.Customer customer = new RazorpayRequest.Customer();
        customer.setName(name);
        customer.setContact("+91"+phoneNumber);
        customer.setEmail(email);
        request.setCustomer(customer);

        request.setDescription(description);

        return razorpayClient.createPaymentLink(request);
    }

    private String generateUniqueReferenceId() {
        // Generate a unique reference ID (e.g., using UUID)
        return "REF" + System.currentTimeMillis();
    }

    @Transactional
    public PaymentRequest createPaymentRequest(PaymentRequest paymentRequest) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
    	String formattedDateTime = LocalDateTime.now().format(formatter);
        paymentRequest.setCreatedAt(formattedDateTime);
        List<UserResponse> users = userClient.getUsers();
        PaymentHistory paymentHistory = new PaymentHistory();

        for(UserResponse user : users){
            paymentHistory.setPaymentRequest(paymentRequest);
            paymentHistory.setAmount(paymentRequest.getAmount());
            paymentHistory.setPaidBy(user.getEmail());
            paymentHistoryRepository.save(paymentHistory);
        }

        publishMessage(paymentRequest.getDescription());
        return paymentRequestRepository.save(paymentRequest);
    }

    @Transactional
    public PaymentHistory createPaymentHistory(PaymentHistory paymentHistory) {
        paymentHistory.setCreatedAt(LocalDateTime.now());
        return paymentHistoryRepository.save(paymentHistory);
    }

    // Other business logic methods
    
    public List<PaymentRequest> getPaymentRequest() {
    	return paymentRequestRepository.findAll();
    }
    
    public List<PaymentHistory> getPaymentHistory() {
    	return paymentHistoryRepository.findAll();
    }
}

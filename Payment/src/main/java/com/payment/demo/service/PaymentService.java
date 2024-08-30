package com.payment.demo.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.payment.demo.entity.PaymentHistory;
import com.payment.demo.entity.PaymentRequest;
import com.payment.demo.repository.PaymentHistoryRepository;
import com.payment.demo.repository.PaymentRequestRepository;

@Service
public class PaymentService {

    @Autowired
    private PaymentRequestRepository paymentRequestRepository;

    @Autowired
    private PaymentHistoryRepository paymentHistoryRepository;

    @Transactional
    public PaymentRequest createPaymentRequest(PaymentRequest paymentRequest) {
    	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss a");
    	 String formattedDateTime = LocalDateTime.now().format(formatter);
        paymentRequest.setCreatedAt(formattedDateTime);
        
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

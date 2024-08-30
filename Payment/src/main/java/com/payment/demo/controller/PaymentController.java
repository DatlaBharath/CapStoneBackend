package com.payment.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.payment.demo.entity.PaymentHistory;
import com.payment.demo.entity.PaymentRequest;
import com.payment.demo.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/requests")
    public ResponseEntity<PaymentRequest> createPaymentRequest( 
        @RequestBody PaymentRequest paymentRequest) {
        PaymentRequest createdRequest = paymentService.createPaymentRequest(paymentRequest);
        return ResponseEntity.ok(createdRequest);
    }

    @PostMapping("/history")
    public ResponseEntity<PaymentHistory> createPaymentHistory(@RequestBody PaymentHistory paymentHistory) {
        PaymentHistory createdHistory = paymentService.createPaymentHistory(paymentHistory);
        return ResponseEntity.ok(createdHistory);
    }

    // Other endpoints
    @GetMapping("/requests")
    public ResponseEntity<List<PaymentRequest>> getPaymentRequest(){
    	return ResponseEntity.ok(paymentService.getPaymentRequest());
    }
    
    @GetMapping("/history")
    public ResponseEntity<List<PaymentHistory>> getPaymentHistory(){
    	return ResponseEntity.ok(paymentService.getPaymentHistory());
    }
}


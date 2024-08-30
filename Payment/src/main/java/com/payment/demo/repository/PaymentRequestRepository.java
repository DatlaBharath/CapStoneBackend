package com.payment.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payment.demo.entity.PaymentRequest;

public interface PaymentRequestRepository extends JpaRepository<PaymentRequest, Long> {
}

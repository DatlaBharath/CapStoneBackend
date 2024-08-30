package com.payment.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.payment.demo.entity.PaymentHistory;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistory, Long> {
}

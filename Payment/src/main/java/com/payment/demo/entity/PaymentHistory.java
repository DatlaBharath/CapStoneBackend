package com.payment.demo.entity;


import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "payment_history")
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "payment_request_id")
    private PaymentRequest paymentRequest;

    private String transactionId;
    private BigDecimal amount;
    private String status;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

	public PaymentHistory(Long id, PaymentRequest paymentRequest, String transactionId, BigDecimal amount,
			String status, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.paymentRequest = paymentRequest;
		this.transactionId = transactionId;
		this.amount = amount;
		this.status = status;
		this.createdAt = createdAt;
	}
	

	public PaymentHistory() {
		super();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PaymentRequest getPaymentRequest() {
		return paymentRequest;
	}

	public void setPaymentRequest(PaymentRequest paymentRequest) {
		this.paymentRequest = paymentRequest;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

    // Getters and Setters
    
}

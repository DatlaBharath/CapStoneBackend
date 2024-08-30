package com.payment.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

import java.math.BigDecimal;


@Entity
@Table(name = "payment_request")
public class PaymentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal amount;
    @Column(nullable = false)
    private String status = "PENDING";
    
    private String userId;

    @Column(name = "created_at", updatable = false)
    private String createdAt;

	
	

	public PaymentRequest(Long id, BigDecimal amount, String status, String userId, String createdAt) {
		super();
		this.id = id;
		this.amount = amount;
		this.status = status;
		this.userId = userId;
		this.createdAt = createdAt;
	}


	public PaymentRequest() {
		super();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	

    // Getters and Setters
    
}

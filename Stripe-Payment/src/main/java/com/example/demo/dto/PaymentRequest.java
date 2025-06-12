package com.example.demo.dto;

public class PaymentRequest {
	
    private Long amount;
    
    private String currency;
    
    private String description;
    
    private String email;

    private String orderId; 

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public PaymentRequest(Long amount, String currency, String description, String email,
			String orderId) {
		super();
		this.amount = amount;
		this.currency = currency;
		this.description = description;
		this.email = email;
		this.orderId = orderId;
	}

    
    
}

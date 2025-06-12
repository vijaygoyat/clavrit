package com.example.demo.dto;

public class PaymentResponse {
	
    private String clientSecret;

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public PaymentResponse(String clientSecret) {
		super();
		this.clientSecret = clientSecret;
	}

    
}


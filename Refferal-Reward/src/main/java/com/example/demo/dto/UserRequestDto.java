package com.example.demo.dto;

public class UserRequestDto {
	
	private String username;
    private String referralCode;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getReferralCode() {
		return referralCode;
	}
	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}
	public UserRequestDto(String username, String referralCode) {
		super();
		this.username = username;
		this.referralCode = referralCode;
	}
	public UserRequestDto() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

}

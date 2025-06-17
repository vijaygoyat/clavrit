package com.example.demo.dto;

public class UserBasicDTO {
	
	private Long id;
    private String username;
    private String referralCode;
    private int points;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
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
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public UserBasicDTO(Long id, String username, String referralCode, int points) {
		super();
		this.id = id;
		this.username = username;
		this.referralCode = referralCode;
		this.points = points;
	}
	public UserBasicDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    

}

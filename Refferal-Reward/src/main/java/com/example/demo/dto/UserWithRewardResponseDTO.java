package com.example.demo.dto;

import java.util.List;

public class UserWithRewardResponseDTO {
	
	private Long id;
    private String username;
    private String referralCode;
    private int points;
    
    private String referralLink;

    private UserBasicDTO referredBy;               
    private List<UserBasicDTO> referredUsers;
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
	public UserBasicDTO getReferredBy() {
		return referredBy;
	}
	public void setReferredBy(UserBasicDTO referredBy) {
		this.referredBy = referredBy;
	}
	public List<UserBasicDTO> getReferredUsers() {
		return referredUsers;
	}
	public void setReferredUsers(List<UserBasicDTO> referredUsers) {
		this.referredUsers = referredUsers;
	}
	
	public String getReferralLink() {
		return referralLink;
	}
	public void setReferralLink(String referralLink) {
		this.referralLink = referralLink;
	}
	
	public UserWithRewardResponseDTO(Long id, String username, String referralCode, int points, String referralLink,
			UserBasicDTO referredBy, List<UserBasicDTO> referredUsers) {
		super();
		this.id = id;
		this.username = username;
		this.referralCode = referralCode;
		this.points = points;
		this.referralLink = referralLink;
		this.referredBy = referredBy;
		this.referredUsers = referredUsers;
	}
	public UserWithRewardResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	} 
    
	
}

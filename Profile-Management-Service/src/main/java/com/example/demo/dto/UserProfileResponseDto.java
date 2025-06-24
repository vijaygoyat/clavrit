package com.example.demo.dto;

import java.time.LocalDateTime;

public class UserProfileResponseDto {
	
	private Long id;

    private String name;

    private String gender;

    private String phoneNumber;

    private String email;

    private String address;

    private String preferences;

    private LocalDateTime registrationTime;

    private LocalDateTime lastUpdatedTime;
    
    private String image_url;

	public UserProfileResponseDto(Long id, String name, String gender, String phoneNumber, String email, String address,
			String preferences, LocalDateTime registrationTime, LocalDateTime lastUpdatedTime) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.preferences = preferences;
		this.registrationTime = registrationTime;
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public UserProfileResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPreferences() {
		return preferences;
	}

	public void setPreferences(String preferences) {
		this.preferences = preferences;
	}

	public LocalDateTime getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(LocalDateTime registrationTime) {
		this.registrationTime = registrationTime;
	}

	public LocalDateTime getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(LocalDateTime lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
    
    

}

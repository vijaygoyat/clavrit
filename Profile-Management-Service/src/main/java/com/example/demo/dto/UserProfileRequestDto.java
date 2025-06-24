package com.example.demo.dto;

public class UserProfileRequestDto {
	
	private String name;

    private String gender;

    private String phoneNumber;

    private String email;

    private String address;

    private String preferences;

	public UserProfileRequestDto(String name, String gender, String phoneNumber, String email, String address,
			String preferences) {
		super();
		this.name = name;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
		this.preferences = preferences;
	}

	public UserProfileRequestDto() {
		super();
		// TODO Auto-generated constructor stub
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
    
    

}

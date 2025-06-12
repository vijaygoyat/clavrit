package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private int points;

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
		this.id = id;
	}


	public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

	public Reward( Long userId, int points) {
		super();
		this.userId = userId;
		this.points = points;
	}

	public Reward() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}


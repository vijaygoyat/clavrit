package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private int points;

    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
		this.id = id;
	}
    
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }


	public Reward(User user, int points) {
		super();
		this.user = user;
		this.points = points;
	}

	public Reward() {
		super();
		// TODO Auto-generated constructor stub
	}
    
    
}


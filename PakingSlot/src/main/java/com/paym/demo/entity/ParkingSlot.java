package com.paym.demo.entity;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "parking_slot")
public class ParkingSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "slot_number", nullable = false)
    private Long slotNumber;

    @Column(name = "date", nullable = false)
    private LocalDate date=LocalDate.now();

    @JoinColumn(name = "user_id")
    private String userId; // Assuming a User entity exists

    @Column(name = "guest_name")
    private String guestName;

	public ParkingSlot(Long id, Long slotNumber, LocalDate date, String guestName) {
		super();
		this.id = id;
		this.slotNumber = slotNumber;
		this.date = date;
		//this.user = user;
		this.guestName = guestName;
	}

	public ParkingSlot() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(Long slotNumber) {
		this.slotNumber = slotNumber;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

    // Getters and Setters
    
    
}

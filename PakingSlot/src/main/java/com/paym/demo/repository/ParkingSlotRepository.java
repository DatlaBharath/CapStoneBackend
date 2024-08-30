package com.paym.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paym.demo.entity.ParkingSlot;

public interface ParkingSlotRepository extends JpaRepository<ParkingSlot, Long> {
    // Define custom query methods if needed
}


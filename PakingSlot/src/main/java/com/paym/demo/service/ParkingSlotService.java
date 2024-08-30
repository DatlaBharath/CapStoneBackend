package com.paym.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paym.demo.entity.ParkingSlot;
import com.paym.demo.repository.ParkingSlotRepository;

@Service
public class ParkingSlotService {

    @Autowired
    private ParkingSlotRepository parkingSlotRepository;

    @Transactional
    public ParkingSlot createParkingSlot(ParkingSlot parkingSlot) {
        return parkingSlotRepository.save(parkingSlot);
    }

    // Additional methods for business logic
    
    public List<ParkingSlot> getParkingSlot(){
    	return parkingSlotRepository.findAll();
    }
}

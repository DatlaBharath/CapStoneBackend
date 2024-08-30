package com.paym.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.paym.demo.entity.ParkingSlot;
import com.paym.demo.service.ParkingSlotService;

@RestController
@RequestMapping("/parking-slots")
public class ParkingSlotController {

    @Autowired
    private ParkingSlotService parkingSlotService;

    @PostMapping
    public ResponseEntity<ParkingSlot> createParkingSlot(@RequestBody ParkingSlot parkingSlot) {
        ParkingSlot createdSlot = parkingSlotService.createParkingSlot(parkingSlot);
        return ResponseEntity.ok(createdSlot);
    }

    // Additional endpoints for CRUD operations
    
    @GetMapping
    public ResponseEntity<List<ParkingSlot>> getParkingSlot(){
    	return ResponseEntity.ok(parkingSlotService.getParkingSlot());
    }
}

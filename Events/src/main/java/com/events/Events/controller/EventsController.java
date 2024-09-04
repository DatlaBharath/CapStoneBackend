package com.events.Events.controller;

import com.events.Events.entity.Events;
import com.events.Events.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins="http://localhost:4200")
public class EventsController {

    @Autowired
    private EventsService eventsService;

    @PostMapping
    public ResponseEntity<Events> addEvents(@RequestBody Events events){
        return ResponseEntity.ok(eventsService.addEvents(events));
    }

    @GetMapping
    public ResponseEntity<List<Events>> getEvents(){
        return ResponseEntity.ok(eventsService.getEvents());
    }

    @DeleteMapping("/{id}")
    public void deleteEvents(@PathVariable Long id){
        eventsService.deleteEvent(id);

    }
}

package com.events.Events.service;

import com.events.Events.entity.Events;
import com.events.Events.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventsService {

    @Autowired
    private EventsRepository eventsRepository;

    public Events addEvents(Events events){
        return eventsRepository.save(events);
    }

    public List<Events> getEvents(){
        return eventsRepository.findAll();
    }

    public void deleteEvent(Long id){
        eventsRepository.deleteById(id);
    }
}

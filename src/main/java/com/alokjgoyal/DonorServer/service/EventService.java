package com.alokjgoyal.DonorServer.service;

import com.alokjgoyal.DonorServer.model.DonationEvent;
import com.alokjgoyal.DonorServer.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    public String addEvent(@RequestBody DonationEvent event) {

        UUID id = event.getId();
        if(id == null)
        {
            id = UUID.randomUUID();
            event.setId(id);
        }
        eventRepository.save(event);
        return "Event saved with ID: " + id;
    }

    public List<DonationEvent> getAllEvents(){
        return eventRepository.findAll();
    }

    public Optional<DonationEvent> getEventById(UUID id){
        return eventRepository.findById(id);
    }

    public DonationEvent updateEvent(DonationEvent event){
        eventRepository.save(event);
        return event;
    }

}
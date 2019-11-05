package com.alokjgoyal.DonorServer.controller;

import com.alokjgoyal.DonorServer.model.DonationEvent;
import com.alokjgoyal.DonorServer.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class EventsController {

    @Autowired
    EventService eventService;

    @GetMapping(value = "/events/test")
    public String test()
    {
        return "Event Service";
    }

    @PostMapping(value = "/events/addEvent", produces = "text/plain")
    public @ResponseBody String addEvent(@RequestBody DonationEvent event){
        return eventService.addEvent(event);
    }

    @GetMapping("/events/all")
    public List<DonationEvent> getAllEvents(){
        return eventService.getAllEvents();
    }

    @GetMapping("/events/view/{id}")
    public Optional<DonationEvent> findEventById(@PathVariable UUID id){
        return eventService.getEventById(id);
    }

    @PutMapping("/events/edit/{id}")
    public String editEvent(@PathVariable("id") UUID id, @RequestBody DonationEvent event){

        event.setId(id);
        eventService.updateEvent(event);
        return "Event Updated";
    }
}

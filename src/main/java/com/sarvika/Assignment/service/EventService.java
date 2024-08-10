package com.sarvika.Assignment.service;

import com.sarvika.Assignment.model.Event;
import com.sarvika.Assignment.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public List<Event> getEventsByPetId(Integer petId) {
        return eventRepository.findAll();
    }

    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public Optional<Event> getEventById(Integer id) {
        return eventRepository.findById(id);
    }
}

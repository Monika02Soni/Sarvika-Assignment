package com.sarvika.Assignment.dto;

import com.sarvika.Assignment.model.Event;
import com.sarvika.Assignment.model.Pet;

import java.util.List;

public class PetWithEventsDTO {
    private Pet pet;
    private List<Event> events;

    // Getters and setters
    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}

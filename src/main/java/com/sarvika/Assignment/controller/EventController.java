package com.sarvika.Assignment.controller;

import com.sarvika.Assignment.dto.EventDTO;
import com.sarvika.Assignment.model.Event;
import com.sarvika.Assignment.model.Pet;
import com.sarvika.Assignment.service.EventService;
import com.sarvika.Assignment.service.PetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pets")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private PetService petService;

    @PostMapping("/{petId}/events")
    public ResponseEntity<?> addEvent(@PathVariable Integer petId, @Valid @RequestBody Event event) {
        Optional<Pet> pet = petService.getPetById(petId);
        if (pet.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet not found");
        }
        event.setPet(pet.get());
        Event savedEvent = eventService.saveEvent(event);
        EventDTO eventDTO = new EventDTO();
        eventDTO.setId(savedEvent.getId());
        eventDTO.setDate(savedEvent.getDate());
        eventDTO.setType(savedEvent.getType());
        eventDTO.setRemark(savedEvent.getRemark());
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
    }
}

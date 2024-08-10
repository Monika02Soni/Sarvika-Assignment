package com.sarvika.Assignment.controller;

import com.sarvika.Assignment.dto.PetWithEventsDTO;
import com.sarvika.Assignment.model.Event;
import com.sarvika.Assignment.model.Pet;
import com.sarvika.Assignment.service.EventService;
import com.sarvika.Assignment.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<Pet>> listPets(@RequestParam(value = "species", required = false) String species) {
        List<Pet> pets = petService.getAllPets(species);
        return ResponseEntity.ok(pets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetWithEventsDTO> getPet(@PathVariable Integer id,
                                         @RequestParam(value = "sortKey", required = false) String sortKey,
                                         @RequestParam(value = "sortOrder", required = false) String sortOrder) {
        Optional<Pet> petOptional = petService.getPetById(id);
        if (petOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Pet pet = petOptional.get();
        List<Event> events = pet.getEvents();

        // Default sorting by date descending
        Comparator<Event> comparator = Comparator.comparing(Event::getDate).reversed();

        if (sortKey != null && !sortKey.isEmpty()) {
            switch (sortKey) {
                case "type":
                    comparator = Comparator.comparing(Event::getType);
                    break;
                case "remark":
                    comparator = Comparator.comparing(Event::getRemark);
                    break;
                default:
                    comparator = Comparator.comparing(Event::getDate).reversed();
                    break;
            }
        }

        if ("desc".equalsIgnoreCase(sortOrder)) {
            comparator = comparator.reversed();
        }

        events.sort(comparator);

        PetWithEventsDTO response = new PetWithEventsDTO();
        response.setPet(pet);
        response.setEvents(events);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePet(@PathVariable Integer id) {
        Optional<Pet> pet = petService.getPetById(id);
        if (pet.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet not found with id: " + id);
        }
        petService.deletePet(id);
        return ResponseEntity.ok("Pet deleted successfully");
    }
}

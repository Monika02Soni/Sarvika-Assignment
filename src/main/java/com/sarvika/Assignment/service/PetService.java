package com.sarvika.Assignment.service;

import com.sarvika.Assignment.model.Event;
import com.sarvika.Assignment.model.Pet;
import com.sarvika.Assignment.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class PetService {
    private static final Logger logger = LoggerFactory.getLogger(PetService.class);

    @Autowired
    private PetRepository petRepository;

    public List<Pet> getAllPets(String species) {
        logger.info("Fetching all pets with species: {}", species);
        if (species != null && !species.isEmpty()) {
            return petRepository.findBySpecies(species);
        }
        return petRepository.findAll();
    }

    public Optional<Pet> getPetById(Integer id) {
        logger.info("Fetching pet with id: {}", id);
        return petRepository.findById(id);
    }

    public Pet savePet(Pet pet) {
        return petRepository.save(pet);
    }

    public void deletePet(Integer id) {
        petRepository.deleteById(id);
    }

    public Pet updatePet(Integer id, Pet petDetails) {
        Optional<Pet> existingPet = petRepository.findById(id);
        if (existingPet.isPresent()) {
            Pet pet = existingPet.get();
            pet.setName(petDetails.getName());
            pet.setSpecies(petDetails.getSpecies());
            pet.getEvents().clear();
            pet.getEvents().addAll(petDetails.getEvents());
            return petRepository.save(pet);
        } else {
            throw new EntityNotFoundException("Pet not found with id " + id);
        }
    }

    public Pet addEventToPet(Integer petId, Event event) {
        Optional<Pet> petOptional = petRepository.findById(petId);
        if (petOptional.isEmpty()) {
            throw new EntityNotFoundException("Pet not found with id " + petId);
        }
        Pet pet = petOptional.get();
        pet.addEvent(event);
        return petRepository.save(pet);
    }

    public Pet removeEventFromPet(Integer petId, Integer eventId) {
        Optional<Pet> petOptional = petRepository.findById(petId);
        if (petOptional.isEmpty()) {
            throw new EntityNotFoundException("Pet not found with id " + petId);
        }
        Pet pet = petOptional.get();
        Event eventToRemove = pet.getEvents().stream()
                .filter(event -> event.getId().equals(eventId))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id " + eventId));
        pet.removeEvent(eventToRemove);
        return petRepository.save(pet);
    }
}

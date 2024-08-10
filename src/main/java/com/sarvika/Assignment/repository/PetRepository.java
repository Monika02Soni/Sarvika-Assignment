package com.sarvika.Assignment.repository;

import com.sarvika.Assignment.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Integer> {
    List<Pet> findBySpecies(String species);
}
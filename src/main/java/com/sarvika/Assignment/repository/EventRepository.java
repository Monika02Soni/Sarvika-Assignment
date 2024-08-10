package com.sarvika.Assignment.repository;

import com.sarvika.Assignment.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}

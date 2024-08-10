package com.sarvika.Assignment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String owner;
    private String species;
    private String sex;
    @Temporal(TemporalType.DATE)
    private Date birth;
    @Temporal(TemporalType.DATE)
    private Date death;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Event> events = new ArrayList<>();

    // Getters and setters
    public void setId(Integer id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getSpecies() {
        return this.species;
    }

    public void setSex(){
        this.sex = sex;
    }

    public String getSex() {
        return this.sex;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getBirth() {
        return this.birth;
    }

    public void setDeath(Date death) {
        this.death = death;
    }

    public Date getDeath() {
        return this.death;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public List<Event> getEvents() {
        return this.events;
    }

    public void addEvent(Event event) {
        events.add(event);
        event.setPet(this);
    }

    public void removeEvent(Event event) {
        events.remove(event);
        event.setPet(null);
    }
}
package com.belyaeva.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
/**
 * Developers team
 * */
@Entity
@Table(name = "team")
public class Team extends AbstractPersistable<Long> {

    @Column(name = "name", unique = true)
    private String name;

    @OneToOne(cascade = ALL)
    private User owner;

    @OneToMany(mappedBy = "team")
    private List<User> participants = new ArrayList<>();

    public String getName() {
        return name;
    }

    public Team setName(String name) {
        this.name = name;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public Team setOwner(User owner) {
        this.owner = owner;
        return this;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }
}

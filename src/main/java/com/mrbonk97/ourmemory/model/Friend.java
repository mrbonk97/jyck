package com.mrbonk97.ourmemory.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String phoneNumber;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    Image image;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "friend_event", inverseJoinColumns = @JoinColumn(name = "event_id"))
    List<Event> eventList;
    @ManyToMany(mappedBy = "friends")
    List<Memory> memories = new ArrayList<>();
}

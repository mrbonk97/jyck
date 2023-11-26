package com.mrbonk97.ourmemory.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Friend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    String phoneNumber;
    @ManyToOne
    User user;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    MediaFile profileImage;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "friend_event", inverseJoinColumns = @JoinColumn(name = "event_id"))
    List<Event> eventList = new ArrayList<>();
    @ManyToMany(mappedBy = "friends")
    List<Memory> memories = new ArrayList<>();
}

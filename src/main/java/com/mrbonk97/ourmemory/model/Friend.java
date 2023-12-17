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
    @ManyToOne
    User user;
    String name;
    String description;
    String phoneNumber;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    MediaFile profileImage;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    List<Event> events = new ArrayList<>();
    @ManyToMany(mappedBy = "friends")
    List<FriendGroup> friendGroup = new ArrayList<>();
}

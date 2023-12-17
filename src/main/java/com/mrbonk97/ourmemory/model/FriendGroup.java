package com.mrbonk97.ourmemory.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class FriendGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    User user;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private MediaFile image;
    private String title;
    private String description;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Event> events = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "friend_group_friend",
            joinColumns = { @JoinColumn(name = "group_id") },
            inverseJoinColumns = { @JoinColumn(name = "friend_id") })
    private List<Friend> friends = new ArrayList<>();
}

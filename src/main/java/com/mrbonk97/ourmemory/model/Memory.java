package com.mrbonk97.ourmemory.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Memory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String title;
    String description;
    Date date;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "memory_image", inverseJoinColumns = @JoinColumn(name = "image_id"))
    List<Image> imageList;

    @ManyToMany
    @JoinTable(name = "memory_friend",
            joinColumns = { @JoinColumn(name = "memory_id") },
            inverseJoinColumns = { @JoinColumn(name = "friend_id") })
    List<Friend> friends = new ArrayList<>();
}
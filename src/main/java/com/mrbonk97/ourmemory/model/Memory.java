package com.mrbonk97.ourmemory.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Memory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    User creator;
    @Column(nullable = false)
    String title;
    String description;
    Date date;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "memory_image", inverseJoinColumns = @JoinColumn(name = "image_id"))
    List<MediaFile> Images = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "memory_friend",
            joinColumns = { @JoinColumn(name = "memory_id") },
            inverseJoinColumns = { @JoinColumn(name = "friend_id") })
    List<Friend> friends = new ArrayList<>();
}
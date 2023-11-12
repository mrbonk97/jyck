package com.mrbonk97.ourmemory.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String email;
    @Column(nullable = false)
    String name;
    @Column(nullable = false)
    String password;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    Image image;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "account_friend", inverseJoinColumns = @JoinColumn(name = "friend_id"))
    List<Friend> friends;
}

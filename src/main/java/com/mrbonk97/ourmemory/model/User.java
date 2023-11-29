package com.mrbonk97.ourmemory.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String email;
    private String name;
    @Column(nullable = false)
    private String password;
    private String phoneNumber;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "account_friend", inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private List<Friend> friends;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private MediaFile profileImage;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Memory> memories;
    @Enumerated(EnumType.STRING)
    private Provider provider = Provider.local;
    private String providerId;

}

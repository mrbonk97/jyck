package com.mrbonk97.ourmemory.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
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
    private String password;
    private String phoneNumber;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private MediaFile profileImage;
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider = AuthProvider.local;
    private String providerId;
    Date emailVerified;
}

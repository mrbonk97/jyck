package com.mrbonk97.ourmemory.model;

import jakarta.persistence.*;

@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String originalName;
    @Column(nullable = false)
    String url;
}

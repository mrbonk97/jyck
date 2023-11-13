package com.mrbonk97.ourmemory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.nio.file.Path;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MediaFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String originalFilename;
    @Column(nullable = false)
    private String url;
    private Long size;

    public MediaFile(String originalFilename, Path destinationFilePath, long size) {
        this.originalFilename = originalFilename;
        this.url = String.valueOf(destinationFilePath);
        this.size = size;
    }


}

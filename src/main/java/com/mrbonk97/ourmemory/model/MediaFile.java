package com.mrbonk97.ourmemory.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.file.Path;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class MediaFile {
    // 이미지를 스토리지에 저장하는 것이 아니라 직접 데이터베이스에 저장하는 방식으로 변경
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Lob
    String image;

    public MediaFile(String image) {
        this.image = image;
    }

//    private String originalFilename;
//    @Column(nullable = false)
//    private String url;
//    private Long size;
//
//    public MediaFile(String originalFilename, Path destinationFilePath, long size) {
//        this.originalFilename = originalFilename;
//        this.url = String.valueOf(destinationFilePath);
//        this.size = size;
//    }


}

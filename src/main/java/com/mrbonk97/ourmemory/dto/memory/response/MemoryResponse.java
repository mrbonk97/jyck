package com.mrbonk97.ourmemory.dto.memory.response;

import com.mrbonk97.ourmemory.model.Friend;
import com.mrbonk97.ourmemory.model.MediaFile;
import com.mrbonk97.ourmemory.model.Memory;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class MemoryResponse {
    private static class F {
        public Long id;
        public String name;
        public MediaFile profileImage;
        public F(Long id, String name, MediaFile profileImage) {
            this.id = id;
            this.name = name;
            this.profileImage = profileImage;
        }
    }
    private Long id;
    private String title;
    private String description;
    private Date date;
    private List<MediaFile> images = new ArrayList<>();
    private List<F> friends = new ArrayList<>();

    public static MemoryResponse fromMemory(Memory memory) {
        MemoryResponse memoryResponse = new MemoryResponse();
        memoryResponse.setId(memory.getId());
        memoryResponse.setTitle(memory.getTitle());
        memoryResponse.setDescription(memory.getDescription());
        memoryResponse.setDate(memory.getDate());
        memoryResponse.setImages(memory.getImages());

        if(memory.getFriends() == null) return memoryResponse;

        for(var e: memory.getFriends()) {
            F f = new F(e.getId(), e.getName(), e.getProfileImage());
            memoryResponse.getFriends().add(f);
        }

        return memoryResponse;
    }
}

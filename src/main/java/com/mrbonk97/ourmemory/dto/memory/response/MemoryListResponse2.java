package com.mrbonk97.ourmemory.dto.memory.response;

import com.mrbonk97.ourmemory.model.MediaFile;
import com.mrbonk97.ourmemory.model.Memory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class MemoryListResponse2 {
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

    Long id;
    String title;
    String description;
    MediaFile thumbnail;
    List<F> friends = new ArrayList<>();
    Date date;

    public static MemoryListResponse2 fromMemory(Memory memory) {
        MemoryListResponse2 memoryListResponse2 = new MemoryListResponse2();
        memoryListResponse2.setId(memory.getId());
        memoryListResponse2.setTitle(memory.getTitle());
        memoryListResponse2.setDescription(memory.getDescription());
        memoryListResponse2.setThumbnail(memory.getImages().get(0));
        memoryListResponse2.setDate(memory.getDate());

        if(memory.getFriends() != null) {
            for (var e : memory.getFriends()) {
                F f = new F(e.getId(), e.getName(), e.getProfileImage());
                memoryListResponse2.getFriends().add(f);
            }
        }

        return memoryListResponse2;
    }
}

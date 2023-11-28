package com.mrbonk97.ourmemory.dto.memory.response;

import com.mrbonk97.ourmemory.model.MediaFile;
import com.mrbonk97.ourmemory.model.Memory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public
class MemoryListResponse {
    Long id;
    String title;
    MediaFile thumbnail;
    Date date;

    public static MemoryListResponse fromMemory(Memory memory) {
        MemoryListResponse memoryListResponse = new MemoryListResponse();
        memoryListResponse.setId(memory.getId());
        memoryListResponse.setTitle(memory.getTitle());
        memoryListResponse.setThumbnail(memory.getImages().get(0));
        memoryListResponse.setDate(memory.getDate());
        return memoryListResponse;
    }
}

package com.mrbonk97.ourmemory.dto.memory.request;

import com.mrbonk97.ourmemory.model.Friend;
import com.mrbonk97.ourmemory.model.MediaFile;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Setter
@Getter
public class MemoryUpdateRequest {
    String title;
    String description;
    Date date;
    HashSet<Long> friendIds = new HashSet<>();
    List<MediaFile> images = new ArrayList<>();
}

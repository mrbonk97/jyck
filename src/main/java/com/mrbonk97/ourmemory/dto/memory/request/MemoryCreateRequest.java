package com.mrbonk97.ourmemory.dto.memory.request;

import com.mrbonk97.ourmemory.model.Friend;
import com.mrbonk97.ourmemory.model.MediaFile;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class MemoryCreateRequest {
    String title;
    String description;
    Date date;
    List<Friend> friends = new ArrayList<>();
    List<MediaFile> images = new ArrayList<>();
}

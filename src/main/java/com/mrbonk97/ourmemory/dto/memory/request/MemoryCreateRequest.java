package com.mrbonk97.ourmemory.dto.memory.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class MemoryCreateRequest {
    String title;
    String description;
    Date date;
    List<Long> friendIdList;

}

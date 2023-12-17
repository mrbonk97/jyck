package com.mrbonk97.ourmemory.dto.friendGroup.request;

import com.mrbonk97.ourmemory.model.Event;
import com.mrbonk97.ourmemory.model.Friend;
import com.mrbonk97.ourmemory.model.MediaFile;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FriendGroupCreateRequest {
    private Long id;
    private String title;
    private String description;
    private MediaFile image;
    private List<Event> events;
    private List<Friend> friends;
}

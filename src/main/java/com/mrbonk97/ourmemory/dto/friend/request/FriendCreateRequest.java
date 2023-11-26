package com.mrbonk97.ourmemory.dto.friend.request;

import com.mrbonk97.ourmemory.model.Event;
import lombok.Getter;

import java.util.List;

@Getter
public class FriendCreateRequest {
    String name;
    String description;
    String phoneNumber;
    List<Event> events;
    String profileImage;
}

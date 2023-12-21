package com.mrbonk97.ourmemory.dto.friend.request;

import com.mrbonk97.ourmemory.model.Event;
import com.mrbonk97.ourmemory.model.FriendGroup;
import com.mrbonk97.ourmemory.model.MediaFile;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class FriendCreateRequest {
    String name;
    String description;
    String phoneNumber;
    List<Event> events;
    MediaFile profileImage;
    List<FriendGroup> friendGroups = new ArrayList<>();
}

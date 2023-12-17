package com.mrbonk97.ourmemory.dto.friend.response;

import com.mrbonk97.ourmemory.model.Event;
import com.mrbonk97.ourmemory.model.Friend;
import com.mrbonk97.ourmemory.model.FriendGroup;
import com.mrbonk97.ourmemory.model.MediaFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FriendResponse {
    @Getter
    @Setter
    @AllArgsConstructor
    private static class FG {
        private Long id;
        private String title;
        private String description;
        private MediaFile image;
    }
    private Long id;
    private String name;
    private String description;
    private String phoneNumber;
    private List<Event> events = new ArrayList<>();
    private MediaFile profileImage;
    private List<FG> friendGroups = new ArrayList<>();

    public static FriendResponse fromFriend(Friend friend) {
        FriendResponse friendResponse = new FriendResponse();
        friendResponse.setId(friend.getId());
        friendResponse.setName(friend.getName());
        friendResponse.setDescription(friend.getDescription());
        friendResponse.setPhoneNumber(friend.getPhoneNumber());
        friendResponse.setEvents(friend.getEvents());
        friendResponse.setProfileImage(friend.getProfileImage());
        if(friend.getFriendGroup() == null) return friendResponse;

        for(var e: friend.getFriendGroup())  friendResponse.getFriendGroups().add(new FG(e.getId(), e.getTitle(), e.getDescription(), e.getImage()));
        return friendResponse;
    }
}

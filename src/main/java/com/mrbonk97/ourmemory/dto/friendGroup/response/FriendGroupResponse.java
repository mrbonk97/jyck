package com.mrbonk97.ourmemory.dto.friendGroup.response;

import com.mrbonk97.ourmemory.model.Event;
import com.mrbonk97.ourmemory.model.FriendGroup;
import com.mrbonk97.ourmemory.model.MediaFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FriendGroupResponse {
    @Getter
    @Setter
    @AllArgsConstructor
    private static class F{
        private Long id;
        private String name;
        private MediaFile profileImage;
        private String phoneNumber;
    }
    Long id;
    Long userId;
    private MediaFile image;
    private String name;
    private String description;
    private List<Event> events = new ArrayList<>();
    private List<F> friends = new ArrayList<>();

    public static FriendGroupResponse fromFriendGroup(FriendGroup friendGroup) {
        FriendGroupResponse friendGroupResponse = new FriendGroupResponse();
        friendGroupResponse.setId(friendGroup.getId());
        friendGroupResponse.setUserId(friendGroup.getUser().getId());
        friendGroupResponse.setImage(friendGroup.getImage());
        friendGroupResponse.setName(friendGroup.getTitle());
        friendGroupResponse.setDescription(friendGroup.getDescription());
        friendGroupResponse.setEvents(friendGroup.getEvents());

        for(var e: friendGroup.getFriends()) friendGroupResponse.getFriends().add(new F(e.getId(), e.getName(), e.getProfileImage(), e.getPhoneNumber()));


        return friendGroupResponse;

    }
}

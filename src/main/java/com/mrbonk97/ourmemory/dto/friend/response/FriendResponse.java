package com.mrbonk97.ourmemory.dto.friend.response;

import com.mrbonk97.ourmemory.model.Event;
import com.mrbonk97.ourmemory.model.Friend;
import com.mrbonk97.ourmemory.model.MediaFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FriendResponse {
    private Long id;
    private String name;
    private String phoneNumber;
    private List<Event> events;
    private MediaFile profileImage;

    public static FriendResponse fromFriend(Friend friend) {
        FriendResponse friendResponse = new FriendResponse();
        friendResponse.setId(friend.getId());
        friendResponse.setName(friend.getName());
        friendResponse.setPhoneNumber(friend.getPhoneNumber());
        friendResponse.setEvents(friend.getEventList());
        friendResponse.setProfileImage(friend.getProfileImage());
        return friendResponse;
    }
}

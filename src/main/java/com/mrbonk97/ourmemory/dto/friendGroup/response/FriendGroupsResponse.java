package com.mrbonk97.ourmemory.dto.friendGroup.response;

import com.mrbonk97.ourmemory.model.FriendGroup;
import com.mrbonk97.ourmemory.model.MediaFile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class FriendGroupsResponse {
    @Getter
    @Setter
    @AllArgsConstructor
    private static class FG {
        private Long id;
        private String title;
        private String description;
        private MediaFile image;
    }
    private List<FG> friendGroups = new ArrayList<>();


    public static FriendGroupsResponse fromFriendGroups(List<FriendGroup> friendGroups) {
        FriendGroupsResponse friendGroupsResponse = new FriendGroupsResponse();
        for(var e: friendGroups)
            friendGroupsResponse.getFriendGroups().add(new FG(
                e.getId(),
                e.getTitle(),
                e.getDescription(),
                e.getImage()
        ));



        return friendGroupsResponse;
    }
}

package com.mrbonk97.ourmemory.controller;

import com.mrbonk97.ourmemory.dto.Response;
import com.mrbonk97.ourmemory.dto.friendGroup.response.FriendGroupsResponse;
import com.mrbonk97.ourmemory.dto.friendGroup.request.FriendGroupCreateRequest;
import com.mrbonk97.ourmemory.dto.friendGroup.response.FriendGroupResponse;
import com.mrbonk97.ourmemory.model.FriendGroup;
import com.mrbonk97.ourmemory.service.FriendGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v2/friend-groups")
@RestController
public class FriendGroupController {
    private final FriendGroupService friendGroupService;

    @GetMapping
    public Response<FriendGroupsResponse> getFriendGroups(Authentication authentication) {
        Long userId = Long.valueOf(authentication.getName());
        List<FriendGroup> friendGroups = friendGroupService.getFriendGroups(userId);
        return Response.success(FriendGroupsResponse.fromFriendGroups(friendGroups));
    }

    @GetMapping("/{friendGroupId}")
    public Response<FriendGroupResponse> getFriendGroup(Authentication authentication, @PathVariable Long friendGroupId) {
        Long userId = Long.valueOf(authentication.getName());
        FriendGroup friendGroup = friendGroupService.getFriendGroup(userId, friendGroupId);
        return Response.success(FriendGroupResponse.fromFriendGroup(friendGroup));
    }

    @PostMapping
    public Response<FriendGroupResponse> createFriendGroup(Authentication authentication, @RequestBody FriendGroupCreateRequest friendGroupCreateRequest) {
        Long userId = Long.valueOf(authentication.getName());
        FriendGroup createdFriendGroup = friendGroupService.createFriendGroup(
                userId,
                friendGroupCreateRequest.getTitle(),
                friendGroupCreateRequest.getDescription(),
                friendGroupCreateRequest.getImage(),
                friendGroupCreateRequest.getEvents(),
                friendGroupCreateRequest.getFriends());

        return Response.success(FriendGroupResponse.fromFriendGroup(createdFriendGroup));
    }

    @PutMapping("/{friendGroupId}")
    public Response<FriendGroupResponse> updateFriendGroup(Authentication authentication, @PathVariable Long friendGroupId, @RequestBody FriendGroupCreateRequest friendGroupCreateRequest) {
        Long userId = Long.valueOf(authentication.getName());
        FriendGroup createdFriendGroup = friendGroupService.updateFriendGroup(
                userId,
                friendGroupId,
                friendGroupCreateRequest.getTitle(),
                friendGroupCreateRequest.getDescription(),
                friendGroupCreateRequest.getImage(),
                friendGroupCreateRequest.getEvents(),
                friendGroupCreateRequest.getFriends());

        return Response.success(FriendGroupResponse.fromFriendGroup(createdFriendGroup));
    }

    @DeleteMapping("/{friendGroupId}")
    public Response<String> deleteFriendGroup(Authentication authentication, @PathVariable Long friendGroupId) {
        Long userId = Long.valueOf(authentication.getName());
        friendGroupService.deleteFriendGroup(userId, friendGroupId);
        return Response.success("친구 그룹 삭제 완료.");
    }



}

package com.mrbonk97.ourmemory.controller;

import com.mrbonk97.ourmemory.dto.Response;
import com.mrbonk97.ourmemory.dto.friend.request.FriendCreateRequest;
import com.mrbonk97.ourmemory.dto.friend.response.FriendResponse;
import com.mrbonk97.ourmemory.model.Friend;
import com.mrbonk97.ourmemory.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/v2/friends")
@RestController
public class FriendController {
    private final FriendService friendService;

    @GetMapping
    public Response<List<FriendResponse>> getFriendList(Authentication authentication) {
        Long userId = Long.valueOf(authentication.getName());


        return Response.success(friendService.getFriendList(userId)
                .stream()
                .map(FriendResponse::fromFriend)
                .collect(Collectors.toList()));
    }

    @PostMapping
    public Response<List<FriendResponse>> createFriend(Authentication authentication, @RequestBody List<FriendCreateRequest> friendCreateRequests) {
        Long userId = Long.valueOf(authentication.getName());
        List<FriendResponse> friendResponse = new ArrayList<>();

        for(var friendCreateRequest: friendCreateRequests) {
            Friend friend = friendService.createFriend(
                    userId,
                    friendCreateRequest.getName(),
                    friendCreateRequest.getDescription(),
                    friendCreateRequest.getPhoneNumber(),
                    friendCreateRequest.getEvents(),
                    friendCreateRequest.getProfileImage(),
                    friendCreateRequest.getFriendGroups()
            );
            if(friend == null) continue;
            friendResponse.add(FriendResponse.fromFriend(friend));
        }
        return Response.success(friendResponse);
    }


    @GetMapping("/{friendId}")
    public Response<FriendResponse> getFriend(Authentication authentication, @PathVariable Long friendId) {
        Long userId = Long.valueOf(authentication.getName());
        Friend friend = friendService.getFriend(userId, friendId);
        return Response.success(FriendResponse.fromFriend(friend));
    }

    @PutMapping("/{friendId}")
    public Response<FriendResponse> updateFriend(Authentication authentication, @PathVariable Long friendId, @RequestBody FriendCreateRequest friendCreateRequest){
        Long userId = Long.valueOf(authentication.getName());


        Friend friend = friendService.updateFriend(
                userId,
                friendId,
                friendCreateRequest.getName(),
                friendCreateRequest.getDescription(),
                friendCreateRequest.getPhoneNumber(),
                friendCreateRequest.getEvents(),
                friendCreateRequest.getProfileImage(),
                friendCreateRequest.getFriendGroups()
        );

        return Response.success(FriendResponse.fromFriend(friend));
    }

    @DeleteMapping("/{friendId}")
    public Response<String> deleteFriend(Authentication authentication, @PathVariable Long friendId) {
        Long userId = Long.valueOf(authentication.getName());
        friendService.deleteFriend(userId, friendId);
        return Response.success(friendId + " 삭제 성공");
    }

}

package com.mrbonk97.ourmemory.service;

import com.mrbonk97.ourmemory.Exception.ErrorCode;
import com.mrbonk97.ourmemory.Exception.OurMemoryException;
import com.mrbonk97.ourmemory.model.*;
import com.mrbonk97.ourmemory.repository.FriendGroupRepository;
import com.mrbonk97.ourmemory.repository.FriendRepository;
import com.mrbonk97.ourmemory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class FriendService {
    private final FriendRepository friendRepository;
    private final FriendGroupRepository friendGroupRepository;
    private final UserRepository userRepository;

    public List<Friend> getFriendList(Long userId) {
        return friendRepository.findAllByUserId(userId);
    }


    public Friend createFriend(Long userId, String name, String description, String phoneNumber, List<Event> events, String profileImage, List<FriendGroup> friendGroups) {
        User user = userRepository.findById(userId).orElseThrow(() -> new OurMemoryException(ErrorCode.USER_NOT_FOUND));

        Friend friend = new Friend();
        friend.setUser(user);
        friend.setName(name);
        friend.setDescription(description);
        friend.setPhoneNumber(phoneNumber);

        if(profileImage!= null) friend.setProfileImage(new MediaFile(profileImage));
        if(events != null) for(Event e: events) friend.getEvents().add(e);

        if(friendGroups != null) {
            for(var e: friendGroups)
            {
                FriendGroup friendGroup = friendGroupRepository.findById(e.getId()).orElseThrow(() -> new OurMemoryException(ErrorCode.FRIEND_GROUP_NOT_FOUND));
                friendGroup.getFriends().add(friend);
                friend.getFriendGroup().add(friendGroup);
            }
        }

        return friendRepository.save(friend);
    }

    public Friend getFriend(Long userId, Long friendId) {
        Friend friend = friendRepository.findById(friendId).orElseThrow(() -> new OurMemoryException(ErrorCode.FRIEND_NOT_FOUND));
        userRepository.findById(userId).orElseThrow(() -> new OurMemoryException(ErrorCode.USER_NOT_FOUND));

        if(!Objects.equals(friend.getUser().getId(), userId)) throw new OurMemoryException(ErrorCode.INVALID_PERMISSION);
        return friend;
    }

    public void deleteFriend(Long userId, Long friendId) {
        Friend friend = friendRepository.findById(friendId).orElseThrow(() -> new OurMemoryException(ErrorCode.FRIEND_NOT_FOUND));
        if(!Objects.equals(friend.getUser().getId(), userId)) throw new OurMemoryException(ErrorCode.INVALID_PERMISSION);
        friendRepository.deleteById(friendId);
    }



    public Friend updateFriend(Long userId, Long friendId, String name, String description, String phoneNumber, List<Event> events, String profileImage, List<FriendGroup> friendGroups) {
        Friend friend = friendRepository.findById(friendId).orElseThrow(() -> new OurMemoryException(ErrorCode.FRIEND_NOT_FOUND));
        userRepository.findById(userId).orElseThrow(() -> new OurMemoryException(ErrorCode.USER_NOT_FOUND));

        if(!Objects.equals(friend.getUser().getId(), userId)) throw new OurMemoryException(ErrorCode.INVALID_PERMISSION);

        friend.setName(name);
        friend.setDescription(description);
        friend.setPhoneNumber(phoneNumber);

        friend.getEvents().clear();
        if(events != null) for(Event e: events) friend.getEvents().add(e);
        if(profileImage != null) friend.setProfileImage(new MediaFile(profileImage));


        return friendRepository.saveAndFlush(friend);
    }
}

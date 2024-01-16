package com.mrbonk97.ourmemory.service;

import com.mrbonk97.ourmemory.Exception.ErrorCode;
import com.mrbonk97.ourmemory.Exception.OurMemoryException;
import com.mrbonk97.ourmemory.model.*;
import com.mrbonk97.ourmemory.repository.EventRepository;
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
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final FriendGroupRepository friendGroupRepository;

    public List<Friend> getFriendList(Long userId) {
        return friendRepository.findAllByUserId(userId);
    }


    public Friend createFriend(Long userId, String name, String description, String phoneNumber, List<Event> events, MediaFile profileImage, List<FriendGroup> friendGroups) {
        User user = userRepository.findById(userId).orElseThrow(() -> new OurMemoryException(ErrorCode.USER_NOT_FOUND));

        Friend friend = new Friend();
        friend.setUser(user);
        friend.setName(name);
        friend.setDescription(description);
        friend.setPhoneNumber(phoneNumber);
        friend.setProfileImage(profileImage);
        friend.setEvents(events);

        for(var e: friendGroups)
        {
            FriendGroup friendGroup = friendGroupRepository.findById(e.getId()).orElseThrow(() -> new OurMemoryException(ErrorCode.FRIEND_GROUP_NOT_FOUND));
            friendGroup.getFriends().add(friend);
            friend.getFriendGroups().add(friendGroup);
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
        for(var e: friend.getFriendGroups()) e.getFriends().remove(friend);
        friendRepository.deleteById(friendId);
    }



    public Friend updateFriend(Long userId, Long friendId, String name, String description, String phoneNumber, List<Event> events, MediaFile profileImage, List<FriendGroup> friendGroups) {
        Friend friend = friendRepository.findById(friendId).orElseThrow(() -> new OurMemoryException(ErrorCode.FRIEND_NOT_FOUND));
        userRepository.findById(userId).orElseThrow(() -> new OurMemoryException(ErrorCode.USER_NOT_FOUND));
        if(!Objects.equals(friend.getUser().getId(), userId)) throw new OurMemoryException(ErrorCode.INVALID_PERMISSION);

        for(var e: friend.getFriendGroups()) e.getFriends().remove(friend);
        friend.getFriendGroups().clear();
        for(var e: friendGroups) { e.getFriends().add(friend); friend.getFriendGroups().add(e); }

        friend.setName(name);
        friend.setDescription(description);
        friend.setPhoneNumber(phoneNumber);
        friend.getEvents().clear();
        for(var e: events) {
            if(e.getId() != null) {
                Event event = eventRepository.findById(e.getId()).orElseThrow(() -> new OurMemoryException(ErrorCode.EVENT_NOT_FOUNT));
                event.setName(e.getName());
                event.setDate(e.getDate());
                friend.getEvents().add(event);
            }
            else friend.getEvents().add(e);
        }

        friend.setProfileImage(profileImage);

        return friendRepository.saveAndFlush(friend);
    }
}
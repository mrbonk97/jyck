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
public class FriendGroupService {
    private final FriendGroupRepository friendGroupRepository;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final FriendRepository friendRepository;

    public FriendGroup createFriendGroup(Long userId, String title, String description, MediaFile image, List<Event> events, List<Friend> friends) {
        User user = userRepository.findById(userId).orElseThrow(() -> new OurMemoryException(ErrorCode.USER_NOT_FOUND));

        FriendGroup friendGroup = new FriendGroup();
        friendGroup.setUser(user);
        friendGroup.setTitle(title);
        friendGroup.setDescription(description);
        friendGroup.setImage(image);
        friendGroup.setEvents(events);

        for(var e: friends) {
            friendGroup.getFriends().add(friendRepository.findById(e.getId()).orElseThrow(() -> new OurMemoryException(ErrorCode.FRIEND_GROUP_NOT_FOUND)));
            e.getFriendGroups().add(friendGroup);
        }

        return friendGroupRepository.save(friendGroup);
    }

    public FriendGroup updateFriendGroup(Long userId, Long friendGroupId, String title, String description, MediaFile image, List<Event> events, List<Friend> friends) {
        // friend는 아이디로 받고 Event는 Object로 받는 이유, 그룹은 구릅에 할당 된 이벤트를 만들 수 있기 때문, 하지만 친구는 그렇지 않다.
        FriendGroup friendGroup = friendGroupRepository.findById(friendGroupId).orElseThrow(() -> new OurMemoryException(ErrorCode.FRIEND_GROUP_NOT_FOUND));
        if(!Objects.equals(friendGroup.getUser().getId(), userId)) throw new OurMemoryException(ErrorCode.INVALID_PERMISSION);

        for(var e: friendGroup.getFriends()) {
            Friend friend = friendRepository.findById(e.getId()).orElseThrow(() -> new OurMemoryException(ErrorCode.FRIEND_GROUP_NOT_FOUND));
            friend.getFriendGroups().remove(friendGroup);
        }
        friendGroup.getFriends().clear();

        for(var e: friends) {
            friendGroup.getFriends().add(friendRepository.findById(e.getId()).orElseThrow(() -> new OurMemoryException(ErrorCode.FRIEND_NOT_FOUND)));
            e.getFriendGroups().add(friendGroup);
        }

        friendGroup.setTitle(title);
        friendGroup.setDescription(description);
        friendGroup.setImage(image);
        friendGroup.getEvents().clear();
        for(var e: events) {
            if(e.getId() != null) {
                Event event = eventRepository.findById(e.getId()).orElseThrow(() -> new OurMemoryException(ErrorCode.FRIEND_GROUP_NOT_FOUND));
                event.setName(e.getName());
                event.setDate(e.getDate());
                friendGroup.getEvents().add(event);
            }
            else friendGroup.getEvents().add(e);
        }

        return friendGroupRepository.saveAndFlush(friendGroup);
    }

    public List<FriendGroup> getFriendGroups(Long userId) {
        return friendGroupRepository.findAllByUserId(userId);
    }

    public FriendGroup getFriendGroup(Long userId, Long friendGroupId) {
        FriendGroup friendGroup = friendGroupRepository.findById(friendGroupId).orElseThrow(() -> new OurMemoryException(ErrorCode.FRIEND_GROUP_NOT_FOUND));
        if(!Objects.equals(friendGroup.getUser().getId(), userId)) throw new OurMemoryException(ErrorCode.INVALID_PERMISSION);
        return friendGroup;
    }


    public void deleteFriendGroup(Long userId, Long friendGroupId) {
        FriendGroup friendGroup = friendGroupRepository.findById(friendGroupId).orElseThrow(() -> new OurMemoryException(ErrorCode.FRIEND_GROUP_NOT_FOUND));
        if(!Objects.equals(friendGroup.getUser().getId(), userId)) throw new OurMemoryException(ErrorCode.INVALID_PERMISSION);
        friendGroupRepository.delete(friendGroup);
    }
}

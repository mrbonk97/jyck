package com.mrbonk97.ourmemory.service;

import com.mrbonk97.ourmemory.Exception.ErrorCode;
import com.mrbonk97.ourmemory.Exception.OurMemoryException;
import com.mrbonk97.ourmemory.model.Event;
import com.mrbonk97.ourmemory.model.Friend;
import com.mrbonk97.ourmemory.model.MediaFile;
import com.mrbonk97.ourmemory.model.User;
import com.mrbonk97.ourmemory.repository.FriendRepository;
import com.mrbonk97.ourmemory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FriendService {
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    // Multipart File
//    public Friend createFriend(Long userId, String name, String description, String phoneNumber, List<Event> events, MultipartFile profileImage) throws IOException {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("사람없음"));
//        Friend friend = new Friend();
//        friend.setName(name);
//        friend.setDescription(description);
//        friend.setPhoneNumber(phoneNumber);
//        if(events != null) for(Event e: events) friend.getEventList().add(e);
//        if(profileImage != null) friend.setMediaFile(MediaFileUtils.convertMultiPartFile(profileImage));
//
//        user.getFriends().add(friend);
//        User savedUser = userRepository.saveAndFlush(user);
//        return savedUser.getFriends().get(savedUser.getFriends().size() - 1);
//    }

//    public Friend updateFriend(Long userId, Long friendId, String name, String description, String phoneNumber, List<Event> events, MultipartFile profileImage) throws IOException {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("사람없음"));
//        Friend friend = friendRepository.findById(friendId).orElseThrow(() -> new RuntimeException("친구없음"));
//        if(user != friend.getUser()) throw new RuntimeException("매칭 안됨");
//        friend.setName(name);
//        friend.setDescription(description);
//        friend.setPhoneNumber(phoneNumber);
//        friend.getEventList().clear();
//        if(events != null) for(Event e: events) friend.getEventList().add(e);
//        if(profileImage != null) friend.setMediaFile(MediaFileUtils.convertMultiPartFile(profileImage));
//
//        return friendRepository.saveAndFlush(friend);
//    }

//    public Friend updateFriend(Long userId, Long friendId, String name, String description, String phoneNumber, List<Event> events, String profileImage) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("사람없음"));
//        Friend friend = friendRepository.findById(friendId).orElseThrow(() -> new RuntimeException("친구없음"));
//        if(user != friend.getUser()) throw new RuntimeException("해당 친구 정보를 수정할 권한 없음");
//        friend.setName(name);
//        friend.setDescription(description);
//        friend.setPhoneNumber(phoneNumber);
//        friend.getEventList().clear();
//        if(events != null) for(Event e: events) friend.getEventList().add(e);
//        if(profileImage != null) friend.setMediaFile(MediaFileUtils.convertMultiPartFile(profileImage));
//
//        return friendRepository.saveAndFlush(friend);
//    }

    public List<Friend> getFriendList(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new OurMemoryException(ErrorCode.USER_NOT_FOUND));
        return user.getFriends();
    }


    public Friend createFriend(Long userId, String name, String description, String phoneNumber, List<Event> events, String profileImage) {
        User user = userRepository.findById(userId).orElseThrow(() -> new OurMemoryException(ErrorCode.USER_NOT_FOUND));
        Friend friend = new Friend();
        friend.setUser(user);
        friend.setName(name);
        friend.setDescription(description);
        friend.setPhoneNumber(phoneNumber);
        if(profileImage!= null) friend.setProfileImage(new MediaFile(profileImage));
        if(events != null) for(Event e: events) friend.getEventList().add(e);

        user.getFriends().add(friend);

        return friendRepository.save(friend);
    }

    public Friend getFriend(Long userId, Long friendId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new OurMemoryException(ErrorCode.USER_NOT_FOUND));
        Friend friend = friendRepository.findById(friendId).orElseThrow(() -> new OurMemoryException(ErrorCode.FRIEND_NOT_FOUND));
        if(user != friend.getUser()) throw new OurMemoryException(ErrorCode.INVALID_PERMISSION);
        return friend;
    }

    public void deleteFriend(Long userId, Long friendId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new OurMemoryException(ErrorCode.USER_NOT_FOUND));
        Friend friend = friendRepository.findById(friendId).orElseThrow(() -> new OurMemoryException(ErrorCode.FRIEND_NOT_FOUND));
        if(user != friend.getUser()) throw new OurMemoryException(ErrorCode.INVALID_PERMISSION);
        friendRepository.deleteById(friendId);
    }



    public Friend updateFriend(Long userId, Long friendId, String name, String description, String phoneNumber, List<Event> events, String profileImage) {
        User user = userRepository.findById(userId).orElseThrow(() -> new OurMemoryException(ErrorCode.USER_NOT_FOUND));
        Friend friend = friendRepository.findById(friendId).orElseThrow(() -> new OurMemoryException(ErrorCode.FRIEND_NOT_FOUND));
        if(user != friend.getUser()) throw new OurMemoryException(ErrorCode.INVALID_PERMISSION);

        friend.setName(name);
        friend.setDescription(description);
        friend.setPhoneNumber(phoneNumber);
        friend.getEventList().clear();
        if(events != null) for(Event e: events) friend.getEventList().add(e);
        if(profileImage != null) friend.setProfileImage(new MediaFile(profileImage));

        return friendRepository.saveAndFlush(friend);
    }
}

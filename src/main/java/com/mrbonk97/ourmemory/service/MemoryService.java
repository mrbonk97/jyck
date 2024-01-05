package com.mrbonk97.ourmemory.service;

import com.mrbonk97.ourmemory.Exception.ErrorCode;
import com.mrbonk97.ourmemory.Exception.OurMemoryException;
import com.mrbonk97.ourmemory.model.Friend;
import com.mrbonk97.ourmemory.model.MediaFile;
import com.mrbonk97.ourmemory.model.Memory;
import com.mrbonk97.ourmemory.model.User;
import com.mrbonk97.ourmemory.repository.FriendRepository;
import com.mrbonk97.ourmemory.repository.MemoryRepository;
import com.mrbonk97.ourmemory.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemoryService {
    private final MemoryRepository memoryRepository;
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    @Transactional
    public Memory createMemory(Long userId, String title, String description, Date date, List<Long> friends, List<MediaFile> images) {
        User user = userRepository.findById(userId).orElseThrow(() -> new OurMemoryException(ErrorCode.USER_NOT_FOUND));
        Memory memory = new Memory();
        memory.setUser(user);
        memory.setTitle(title);
        memory.setDescription(description);
        memory.setDate(date);
        memory.setImages(images);

        for(var e: friends)
        {
            Friend friend = friendRepository.findById(e).orElseThrow(() -> new OurMemoryException(ErrorCode.FRIEND_NOT_FOUND));
            if(!friend.getUser().equals(user)) throw new OurMemoryException(ErrorCode.INVALID_PERMISSION);
            memory.getFriends().add(friend);
            friend.getMemories().add(memory);
        }

        return memoryRepository.save(memory);
    }

    @Transactional
    public Memory updateMemory(Long userId, Long memoryId, String title, String description, Date date, HashSet<Long> friends, List<MediaFile> images) {
        User user = userRepository.findById(userId).orElseThrow(() -> new OurMemoryException(ErrorCode.USER_NOT_FOUND));
        Memory memory = memoryRepository.findById(memoryId).orElseThrow(() -> new OurMemoryException(ErrorCode.POST_NOT_FOUND));
        if(!memory.getUser().getId().equals(userId)) throw new OurMemoryException(ErrorCode.INVALID_PERMISSION);

        memory.setTitle(title);
        memory.setDescription(description);
        memory.setDate(date);

        HashSet<Long> deleteId = new HashSet<>();
        for(var e: images) if(e.getId() != null) deleteId.add(e.getId());
        memory.getImages().removeIf((e) -> !deleteId.contains(e.getId()));

        for(var e: images) {
            if(deleteId.contains(e.getId())) continue;
            memory.getImages().add(e);
        }

        System.out.println(friends.toString());

        memory.getFriends().removeIf((e) -> !friends.contains(e.getId()));

        for(var e: friends) {
            Friend friend = friendRepository.findById(e).orElseThrow(() -> new OurMemoryException(ErrorCode.FRIEND_NOT_FOUND));
            if(!friend.getUser().equals(user)) throw new OurMemoryException(ErrorCode.INVALID_PERMISSION);
            System.out.println(friend.getName() + " " + friend.getId());
            memory.getFriends().add(friend);
            friend.getMemories().add(memory);
        }

        return memoryRepository.saveAndFlush(memory);
    }

    @Transactional
    public void deleteMemory(Long userId, Long memoryId) {
        Memory memory = memoryRepository.findById(memoryId).orElseThrow(() -> new OurMemoryException(ErrorCode.POST_NOT_FOUND));
        if(!memory.getUser().getId().equals(userId)) throw new OurMemoryException(ErrorCode.INVALID_PERMISSION);
        memoryRepository.deleteById(memoryId);
    }

    public List<Memory> getMemories(Long userId) {
        return memoryRepository.findAllByUserId(userId);
    }


    public Memory getSingleMemory(Long userId, Long memoryId) {
        Memory memory = memoryRepository.findById(memoryId).orElseThrow(() -> new OurMemoryException(ErrorCode.POST_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(() -> new OurMemoryException(ErrorCode.USER_NOT_FOUND));
        if(!memory.getUser().getId().equals(userId)) throw new OurMemoryException(ErrorCode.INVALID_PERMISSION);
        return memory;
    }
}

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
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemoryService {
    private final MemoryRepository memoryRepository;
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    public List<Memory> getList(Long userId) {
        return memoryRepository.findAllByUserId(userId);
    }

    @Transactional
    public void deleteMemory(Long userId, Long memoryId) {
        Memory memory = memoryRepository.findById(memoryId).orElseThrow(() -> new OurMemoryException(ErrorCode.POST_NOT_FOUND));
        if(!memory.getUser().getId().equals(userId)) throw new OurMemoryException(ErrorCode.INVALID_PERMISSION);
        memoryRepository.deleteById(memoryId);
    }

    @Transactional
    public Memory createMemory(Long userId, String title, String description, Date date, List<Long> friendIds, List<String> images) {
        User user = userRepository.findById(userId).orElseThrow(() -> new OurMemoryException(ErrorCode.USER_NOT_FOUND));
        Memory memory = new Memory();
        memory.setUser(user);
        memory.setTitle(title);
        memory.setDescription(description);
        memory.setDate(date);
        if(friendIds != null)
        {
            for(var e: friendIds)
            {
                Friend friend = friendRepository.findById(e).orElseThrow(() -> new OurMemoryException(ErrorCode.FRIEND_NOT_FOUND));
                if(!friend.getUser().equals(user)) throw new OurMemoryException(ErrorCode.INVALID_PERMISSION);
                memory.getFriends().add(friend);
            }
        }

        if(images != null)
        {
            for(var e: images)
            {
                System.out.println(e);
                MediaFile image = new MediaFile();
                image.setImage(e);
                memory.getImages().add(image);
            }
        }

        return memoryRepository.save(memory);
    }


    @Transactional
    public Memory updateMemory(Long userId, Long memoryId, String title, String description, Date date, List<Long> friendIds, List<String> images) {
        Memory memory = memoryRepository.findById(memoryId).orElseThrow(() -> new OurMemoryException(ErrorCode.POST_NOT_FOUND));
        if(!memory.getUser().getId().equals(userId)) throw new OurMemoryException(ErrorCode.INVALID_PERMISSION);

        memory.setTitle(title);
        memory.setDescription(description);
        memory.setDate(date);
        memory.getFriends().clear();
        if(friendIds != null)
        {
            for(var e: friendIds)
            {
                Friend friend = friendRepository.findById(e).orElseThrow(() -> new OurMemoryException(ErrorCode.FRIEND_NOT_FOUND));
                if(!friend.getUser().getId().equals(userId)) throw new OurMemoryException(ErrorCode.INVALID_PERMISSION);
                memory.getFriends().add(friend);
            }
        }
        memory.getImages().clear();
        if(images != null)
        {
            for(var e: images)
            {
                MediaFile image = new MediaFile();
                image.setImage(e);
                memory.getImages().add(image);
            }
        }

        return memoryRepository.saveAndFlush(memory);
    }


    public Memory getSingleMemory(Long userId, Long memoryId) {
        Memory memory = memoryRepository.findById(memoryId).orElseThrow(() -> new OurMemoryException(ErrorCode.POST_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(() -> new OurMemoryException(ErrorCode.USER_NOT_FOUND));
        if(!memory.getUser().getId().equals(userId)) throw new OurMemoryException(ErrorCode.INVALID_PERMISSION);
        return memory;
    }
}

package com.mrbonk97.ourmemory.service;

import com.mrbonk97.ourmemory.model.Friend;
import com.mrbonk97.ourmemory.model.Memory;
import com.mrbonk97.ourmemory.model.User;
import com.mrbonk97.ourmemory.repository.FriendRepository;
import com.mrbonk97.ourmemory.repository.MemoryRepository;
import com.mrbonk97.ourmemory.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemoryService {
    private final MemoryRepository memoryRepository;
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    public List<Memory> getList(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("ASD"));
        return memoryRepository.findAllByCreator(user);
    }

    public Memory createMemory(Long userId, String title, String description, Date date, List<Long> friendIdList, List<MultipartFile> images) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("ASD"));
        Memory memory = new Memory();
        memory.setCreator(user);
        memory.setDate(date);
        memory.setTitle(title);
        memory.setDate(date);
        memory.setDescription(description);

        if(images != null) {
            for (MultipartFile multipartFile : images) {


            }
        }

        return memoryRepository.save(memory);
    }

    public Memory updateMemory(Long memoryId, Long userId, String title, String description, Date date, List<Long> friendIdList, List<MultipartFile> images) throws IOException {
        Memory memory = memoryRepository.findById(memoryId).orElseThrow(() -> new RuntimeException("그런거 없따"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("그런 사람 없다"));
        if(memory.getCreator() != user) throw new RuntimeException("매칭이 안된다");

        memory.setTitle(title);
        memory.setDescription(description);
        memory.setDate(date);

        if(friendIdList != null) {
            for (Long friendId : friendIdList) {
                Friend friend = friendRepository.findById(friendId).orElseThrow(() -> new RuntimeException("그런 친구는 없다"));
                memory.getFriends().add(friend);
            }
        }

        if(images != null) {

        }

        return memoryRepository.saveAndFlush(memory);
    }

    public void deleteMemory(Long postId) {
        memoryRepository.deleteById(postId);
    }

    public Memory getSingleMemory(Long memoryId) {
        return memoryRepository.findById(memoryId).orElseThrow(() -> new RuntimeException("포스트 없음"));
    }
}

package com.mrbonk97.ourmemory.controller;

import com.mrbonk97.ourmemory.dto.memory.request.MemoryCreateRequest;
import com.mrbonk97.ourmemory.model.Memory;
import com.mrbonk97.ourmemory.service.MemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v2/memories")
@RestController
public class MemoryController {
    private final MemoryService memoryService;

    @GetMapping("/")
    public List<Memory> getMemoryList(Authentication authentication) {
        Long userId = Long.valueOf(authentication.getName());
        return memoryService.getList(userId);
    }

    @PostMapping("/")
    public Memory createMemory(Authentication authentication,MemoryCreateRequest memoryCreateRequest, @RequestPart(value="file",required = false) List<MultipartFile> mediaFiles) throws IOException {
        Long userId = Long.valueOf(authentication.getName());

        return  memoryService.createMemory(
                userId,
                memoryCreateRequest.getTitle(),
                memoryCreateRequest.getDescription(),
                memoryCreateRequest.getDate(),
                memoryCreateRequest.getFriendIdList(),
                mediaFiles
        );

    }

    @GetMapping("/{memoryId}")
    public Memory getMemory(@PathVariable Long memoryId) {
        return memoryService.getSingleMemory(memoryId);
    }

    @PutMapping("/{memoryId}")
    public Memory updateMemory(@PathVariable Long memoryId, Authentication authentication, @RequestBody MemoryCreateRequest memoryCreateRequest, @RequestPart(value="file",required = false) List<MultipartFile> mediaFiles) throws IOException {
        // Long postId, Long userId, String title, String description, Date date, List<Long> friendIdList, List<MultipartFile> images) throws IOException {
        Long userId = Long.valueOf(authentication.getName());
        return memoryService.updateMemory(
                memoryId,
                userId,
                memoryCreateRequest.getTitle(),
                memoryCreateRequest.getDescription(),
                memoryCreateRequest.getDate(),
                memoryCreateRequest.getFriendIdList(),
                mediaFiles
        );
    }
}

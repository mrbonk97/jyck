package com.mrbonk97.ourmemory.controller;

import com.mrbonk97.ourmemory.dto.Response;
import com.mrbonk97.ourmemory.dto.memory.request.MemoryCreateRequest;
import com.mrbonk97.ourmemory.dto.memory.response.MemoryListResponse;
import com.mrbonk97.ourmemory.dto.memory.response.MemoryResponse;
import com.mrbonk97.ourmemory.model.Memory;
import com.mrbonk97.ourmemory.service.MemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RequestMapping("/api/v2/memories")
@RestController
public class MemoryController {
    private final MemoryService memoryService;

    @GetMapping()
    public Response<List<MemoryListResponse>> getMemoryList(Authentication authentication) {
        Long userId = Long.valueOf(authentication.getName());
        return Response.success(memoryService.getList(userId).stream().map(MemoryListResponse::fromMemory).collect(Collectors.toList()));
    }

    @GetMapping("/{memoryId}")
    public Response<MemoryResponse> getMemory(Authentication authentication, @PathVariable Long memoryId) {
        Long userId = Long.valueOf(authentication.getName());
        Memory memory = memoryService.getSingleMemory(userId, memoryId);
        return Response.success(MemoryResponse.fromMemory(memory));
    }

    @PostMapping()
    public Response<MemoryResponse> createMemory(Authentication authentication, @RequestBody MemoryCreateRequest memoryCreateRequest) {
        Long userId = Long.valueOf(authentication.getName());
        Memory memory = memoryService.createMemory(
                userId,
                memoryCreateRequest.getTitle(),
                memoryCreateRequest.getDescription(),
                memoryCreateRequest.getDate(),
                memoryCreateRequest.getFriendIds(),
                memoryCreateRequest.getImages()
        );
        MemoryResponse memoryResponse = MemoryResponse.fromMemory(memory);
        return Response.success(memoryResponse);
    }

    @PutMapping("/{memoryId}")
    public Response<MemoryResponse> updateMemory(Authentication authentication, @PathVariable Long memoryId, @RequestBody MemoryCreateRequest memoryCreateRequest) {
        Long userId = Long.valueOf(authentication.getName());
        Memory memory = memoryService.updateMemory(
                userId,
                memoryId,
                memoryCreateRequest.getTitle(),
                memoryCreateRequest.getDescription(),
                memoryCreateRequest.getDate(),
                memoryCreateRequest.getFriendIds(),
                memoryCreateRequest.getImages()
        );

        return Response.success(MemoryResponse.fromMemory(memory));
    }

    @DeleteMapping("/{memoryId}")
    public Response<String> deleteMemory(Authentication authentication, @PathVariable Long memoryId) {
        Long userId = Long.valueOf(authentication.getName());
        memoryService.deleteMemory(userId, memoryId);
        return Response.success("삭제 완료");
    }

}
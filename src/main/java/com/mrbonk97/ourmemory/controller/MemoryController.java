package com.mrbonk97.ourmemory.controller;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/memories")
@RestController
public class MemoryController {

    @GetMapping("/")
    public void getMemoryList() {}

    @PostMapping("/create")
    public void createMemory() {}

    @GetMapping("/{friendId}")
    public void getMemory() {}

    @PutMapping("/{friendId}")
    public void updateMemory() {}

    @DeleteMapping("/{friendId}")
    public void  deleteMemory() {}
}

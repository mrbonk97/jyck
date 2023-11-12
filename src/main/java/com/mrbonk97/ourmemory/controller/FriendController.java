package com.mrbonk97.ourmemory.controller;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/friends")
@RestController
public class FriendController {

    @GetMapping("/")
    public void getFriendList() {}

    @PostMapping("/create")
    public void createFriend() {}

    @GetMapping("/{friendId}")
    public void getFriend() {}

    @PutMapping("/{friendId}")
    public void updateFriend() {}

    @DeleteMapping("/{friendId}")
    public void  deleteFriend() {}

}

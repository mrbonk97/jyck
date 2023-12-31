package com.mrbonk97.ourmemory.repository;

import com.mrbonk97.ourmemory.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {
    public List<Friend> findAllByUserId(Long userId);
}

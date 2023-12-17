package com.mrbonk97.ourmemory.repository;

import com.mrbonk97.ourmemory.model.FriendGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendGroupRepository extends JpaRepository<FriendGroup, Long> {
    public List<FriendGroup> findAllByUserId(Long userId);
}

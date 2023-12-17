package com.mrbonk97.ourmemory.repository;

import com.mrbonk97.ourmemory.model.Memory;
import com.mrbonk97.ourmemory.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoryRepository extends JpaRepository<Memory, Long> {
    List<Memory> findAllByUserId(Long userId);
}

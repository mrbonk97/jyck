package com.mrbonk97.ourmemory.repository;

import com.mrbonk97.ourmemory.model.Memory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoryRepository extends JpaRepository<Memory, Long> {
}

package com.mrbonk97.ourmemory.repository;

import com.mrbonk97.ourmemory.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}

package com.openclassrooms.ycyw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.openclassrooms.ycyw.models.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
    Optional<Chat> findFirstBySenderIdOrderByTimestampDesc(Integer senderId);

    @Query("SELECT MAX(c.conversationId) FROM Chat c")
    Optional<Integer> findMaxConversationId();
}


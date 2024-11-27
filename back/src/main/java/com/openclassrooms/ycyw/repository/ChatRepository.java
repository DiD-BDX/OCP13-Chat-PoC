package com.openclassrooms.ycyw.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.ycyw.models.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {
}


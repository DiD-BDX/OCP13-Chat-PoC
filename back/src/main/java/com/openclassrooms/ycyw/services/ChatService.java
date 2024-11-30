package com.openclassrooms.ycyw.services;

import com.openclassrooms.ycyw.models.Chat;
import com.openclassrooms.ycyw.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    public Chat save(Chat chat) {
        return chatRepository.save(chat);
    }
}

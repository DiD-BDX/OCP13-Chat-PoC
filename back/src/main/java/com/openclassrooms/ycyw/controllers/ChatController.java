package com.openclassrooms.ycyw.controllers;

import com.openclassrooms.ycyw.dto.ChatDTO;
import com.openclassrooms.ycyw.mapper.ChatMapper;
import com.openclassrooms.ycyw.models.Chat;
import com.openclassrooms.ycyw.services.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatMapper chatMapper;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatDTO sendMessage(ChatDTO chatDTO) {
        Chat chat = chatMapper.toEntity(chatDTO);
        Chat savedChat = chatService.save(chat);
        return chatMapper.toDto(savedChat);
    }
}

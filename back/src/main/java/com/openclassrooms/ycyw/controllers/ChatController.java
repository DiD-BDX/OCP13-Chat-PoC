package com.openclassrooms.ycyw.controllers;

import com.openclassrooms.ycyw.dto.ChatDTO;
import com.openclassrooms.ycyw.mapper.ChatMapper;
import com.openclassrooms.ycyw.models.Chat;
import com.openclassrooms.ycyw.models.Utilisateur;
import com.openclassrooms.ycyw.services.ChatService;
import com.openclassrooms.ycyw.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private ChatService chatService;

    @Autowired
    private UtilisateurService utilisateurService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatDTO sendMessage(ChatDTO chatDTO) {
        Utilisateur sender = utilisateurService.findById(chatDTO.getSenderId());
        Chat chat = ChatMapper.toEntity(chatDTO, sender);
        Chat savedChat = chatService.save(chat);
        return ChatMapper.toDto(savedChat);
    }
}
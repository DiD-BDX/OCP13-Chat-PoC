package com.openclassrooms.ycyw.controllers;

import com.openclassrooms.ycyw.dto.ChatDTO;
import com.openclassrooms.ycyw.mapper.ChatMapper;
import com.openclassrooms.ycyw.models.Chat;
import com.openclassrooms.ycyw.models.Utilisateur;
import com.openclassrooms.ycyw.services.ChatService;
import com.openclassrooms.ycyw.services.UtilisateurService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    
    private static final Logger logger = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private ChatService chatService;

    @Autowired
    private UtilisateurService utilisateurService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatDTO sendMessage(ChatDTO chatDTO) {
        logger.info("------------ChatControllerJAVA : Received message: {}", chatDTO);
        Utilisateur sender = utilisateurService.findById(chatDTO.getSenderId());
        Chat chat = ChatMapper.toEntity(chatDTO, sender);
        logger.info("------------ChatControllerJAVA : Mapped to entity: {}", chat);
        Chat savedChat = chatService.save(chat);
        logger.info("------------ChatControllerJAVA : Saved chat: {}", savedChat);
        ChatDTO response = ChatMapper.toDto(savedChat);
        logger.info("------------ChatControllerJAVA : Sending message: {}", response);
        return response;
    }
}
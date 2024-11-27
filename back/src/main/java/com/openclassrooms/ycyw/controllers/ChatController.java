package com.openclassrooms.ycyw.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.openclassrooms.ycyw.dto.ChatDTO;
import com.openclassrooms.ycyw.mapper.ChatMapper;
import com.openclassrooms.ycyw.models.Chat;
import com.openclassrooms.ycyw.models.Utilisateur;
import com.openclassrooms.ycyw.repository.ChatRepository;
import com.openclassrooms.ycyw.repository.UtilisateurRepository;

import org.springframework.messaging.handler.annotation.Payload;

@Controller
public class ChatController {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private ChatMapper chatMapper;

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages") // Diffuse le message aux abonnés
    public ChatDTO sendMessage(@Payload ChatDTO chatDTO) {
        // Récupérer l'utilisateur à partir de senderId
        Utilisateur sender = utilisateurRepository.findById(chatDTO.getSenderId())
            .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        // Mapper le DTO vers l'entité
        Chat chat = chatMapper.toEntity(chatDTO);
        chat.setSender(sender);
        chat.setTimestamp(LocalDateTime.now());

        // Sauvegarder le message dans la base de données
        Chat savedChat = chatRepository.save(chat);

        // Retourner le message sauvegardé comme DTO
        return chatMapper.toDTO(savedChat);
    }
}


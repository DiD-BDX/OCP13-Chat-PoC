package com.openclassrooms.ycyw.services;

import com.openclassrooms.ycyw.models.Chat;
import com.openclassrooms.ycyw.repository.ChatRepository;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatService {

    private static final Logger logger = LoggerFactory.getLogger(ChatService.class);

    @Autowired
    private ChatRepository chatRepository;

    @Transactional
    public Chat save(Chat chat) {
        logger.info("------------ChatServiceJAVA : Saving chat: {}", chat);
        Chat savedChat = chatRepository.save(chat);
        logger.info("------------ChatServiceJAVA : Saved chat: {}", savedChat);
        return savedChat;
    }

    // Nouvelle méthode pour obtenir ou créer l'ID de la conversation
    @Transactional(readOnly = true)
    public Integer getOrCreateConversationIdByUserId(Integer userId) {
        // Rechercher la dernière conversation de l'utilisateur
        Optional<Chat> chat = chatRepository.findFirstBySenderIdOrderByTimestampDesc(userId);
        return chat.map(Chat::getConversationId).orElseGet(() -> createNewConversationId());
    }

    // Méthode pour créer un nouvel ID de conversation
    private Integer createNewConversationId() {
        // Logique pour générer un nouvel ID de conversation
        // Par exemple, en utilisant un générateur de séquence ou une autre logique
        return chatRepository.findMaxConversationId().orElse(0) + 1;
    }
}

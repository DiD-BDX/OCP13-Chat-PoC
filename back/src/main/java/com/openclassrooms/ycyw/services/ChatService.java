package com.openclassrooms.ycyw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import com.openclassrooms.ycyw.models.Chat;
import com.openclassrooms.ycyw.repository.ChatRepository;

/**
 * Service pour gérer les opérations liées aux messages de chat.
 */
@Service
public class ChatService {

    @Autowired
    private ChatRepository chatRepository;

    /**
     * Sauvegarde un message de chat.
     *
     * @param chat le message de chat à sauvegarder
     * @return le message de chat sauvegardé
     */
    @Transactional
    public Chat save(Chat chat) {
        return chatRepository.save(chat);
    }

    /**
     * Obtient ou crée un identifiant de conversation pour un utilisateur donné.
     *
     * @param userId l'identifiant de l'utilisateur
     * @return l'identifiant de la conversation
     */
    @Transactional(readOnly = true)
    public Integer getOrCreateConversationIdByUserId(Integer userId) {
        Optional<Chat> chat = chatRepository.findFirstBySenderIdOrderByTimestampDesc(userId);
        return chat.map(Chat::getConversationId).orElseGet(this::createNewConversationId);
    }

    /**
     * Crée un nouvel identifiant de conversation.
     *
     * @return le nouvel identifiant de conversation
     */
    private Integer createNewConversationId() {
        return chatRepository.findMaxConversationId().orElse(0) + 1;
    }
}
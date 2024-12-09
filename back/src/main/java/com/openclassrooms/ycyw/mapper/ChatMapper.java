package com.openclassrooms.ycyw.mapper;

import com.openclassrooms.ycyw.dto.ChatDTO;
import com.openclassrooms.ycyw.models.Chat;
import com.openclassrooms.ycyw.models.Utilisateur;

/**
 * Interface pour mapper les entités Chat et ChatDTO.
 */
public interface ChatMapper {

    /**
     * Convertit une entité Chat en DTO ChatDTO.
     *
     * @param chat l'entité Chat à convertir
     * @return le DTO ChatDTO correspondant
     */
    static ChatDTO toDto(Chat chat) {
        if (chat == null) {
            return null;
        }

        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setId(chat.getId());
        chatDTO.setSenderId(chat.getSender().getId());
        chatDTO.setConversationId(chat.getConversationId());
        chatDTO.setContent(chat.getContent());
        chatDTO.setTimestamp(chat.getTimestamp());
        chatDTO.setStatus(chat.getStatus());

        return chatDTO;
    }

    /**
     * Convertit un DTO ChatDTO en entité Chat.
     *
     * @param chatDTO le DTO ChatDTO à convertir
     * @param sender l'utilisateur qui envoie le message
     * @return l'entité Chat correspondante
     */
    static Chat toEntity(ChatDTO chatDTO, Utilisateur sender) {
        if (chatDTO == null || sender == null) {
            return null;
        }

        Chat chat = new Chat();
        chat.setId(chatDTO.getId());
        chat.setSender(sender);
        chat.setConversationId(chatDTO.getConversationId());
        chat.setContent(chatDTO.getContent());
        chat.setTimestamp(chatDTO.getTimestamp());
        chat.setStatus(chatDTO.getStatus());

        return chat;
    }
}
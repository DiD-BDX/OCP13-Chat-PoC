package com.openclassrooms.ycyw.mapper;

import com.openclassrooms.ycyw.dto.ChatDTO;
import com.openclassrooms.ycyw.models.Chat;
import com.openclassrooms.ycyw.models.Utilisateur;

public interface ChatMapper {

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

    static Chat toEntity(ChatDTO chatDTO, Utilisateur sender) {
        if (chatDTO == null) {
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
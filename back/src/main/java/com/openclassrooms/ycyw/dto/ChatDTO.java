package com.openclassrooms.ycyw.dto;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * Classe de transfert de données pour les messages de chat.
 */
@Data
public class ChatDTO {
    /**
     * Identifiant unique du message de chat.
     */
    private Integer id;

    /**
     * Identifiant de l'expéditeur du message.
     */
    private Integer senderId;

    /**
     * Identifiant de la conversation à laquelle appartient le message.
     */
    private Integer conversationId;

    /**
     * Contenu du message de chat.
     */
    private String content;

    /**
     * Horodatage du message de chat.
     */
    private LocalDateTime timestamp;

    /**
     * Statut du message de chat (ex: envoyé, reçu, lu).
     */
    private String status;
}
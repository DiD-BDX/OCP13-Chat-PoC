package com.openclassrooms.ycyw.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Entité représentant un message de chat.
 */
@Entity
@Data
public class Chat {
    /**
     * Identifiant unique du message de chat.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Utilisateur qui envoie le message de chat.
     */
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Utilisateur sender;

    /**
     * Identifiant de la conversation à laquelle appartient le message.
     */
    @Column(name = "conversation_id")
    private Integer conversationId;

    /**
     * Contenu du message de chat.
     */
    @Column(columnDefinition = "TEXT")
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
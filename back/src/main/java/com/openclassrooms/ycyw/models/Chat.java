package com.openclassrooms.ycyw.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Utilisateur sender;

    @Column(name = "conversation_id")
    private Integer conversationId;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime timestamp;

    private String status;
}

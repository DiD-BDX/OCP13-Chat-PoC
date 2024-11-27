package com.openclassrooms.ycyw.dto;

import java.time.LocalDateTime;

import lombok.*;

@Data
public class ChatDTO {
    private Long id;
    private Long senderId; // ID de l'utilisateur qui envoie le message
    private String content;
    private LocalDateTime timestamp;
    private String status;
}


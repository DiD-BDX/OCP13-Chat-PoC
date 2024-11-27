package com.openclassrooms.ycyw.dto;

import java.time.LocalDateTime;

import lombok.*;

@Data
public class UtilisateurDTO {
    private Long id;
    private String nom;
    private String email;
    private LocalDateTime dateInscription;
}


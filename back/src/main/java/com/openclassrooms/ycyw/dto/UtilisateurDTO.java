package com.openclassrooms.ycyw.dto;

import lombok.Data;
import java.time.LocalDate;

/**
 * Classe de transfert de données pour les utilisateurs.
 */
@Data
public class UtilisateurDTO {
    /**
     * Identifiant unique de l'utilisateur.
     */
    private Integer id;

    /**
     * Nom de l'utilisateur.
     */
    private String nom;

    /**
     * Prénom de l'utilisateur.
     */
    private String prénom;

    /**
     * Adresse email de l'utilisateur.
     */
    private String email;

    /**
     * Date de naissance de l'utilisateur.
     */
    private LocalDate dateNaissance;
}
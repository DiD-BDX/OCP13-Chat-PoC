package com.openclassrooms.ycyw.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

/**
 * Entité représentant un utilisateur.
 */
@Entity
@Data
public class Utilisateur {
    /**
     * Identifiant unique de l'utilisateur.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * Mot de passe de l'utilisateur.
     */
    @Column(name = "mot_de_passe")
    private String motDePasse;

    /**
     * Date de naissance de l'utilisateur.
     */
    @Column(name = "date_naissance")
    private LocalDate dateNaissance;
}
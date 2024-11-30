package com.openclassrooms.ycyw.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;
    private String prénom;
    private String email;
    private String motDePasse;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;
}

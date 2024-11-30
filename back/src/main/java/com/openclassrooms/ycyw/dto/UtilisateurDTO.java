package com.openclassrooms.ycyw.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UtilisateurDTO {
    private Integer id;
    private String nom;
    private String prénom;
    private String email;
    private LocalDate dateNaissance;
}

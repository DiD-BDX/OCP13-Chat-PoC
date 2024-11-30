package com.openclassrooms.ycyw.mapper;

import com.openclassrooms.ycyw.dto.UtilisateurDTO;
import com.openclassrooms.ycyw.models.Utilisateur;

public interface UtilisateurMapper {

    static UtilisateurDTO toDto(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        utilisateurDTO.setId(utilisateur.getId());
        utilisateurDTO.setNom(utilisateur.getNom());
        utilisateurDTO.setPrénom(utilisateur.getPrénom());
        utilisateurDTO.setEmail(utilisateur.getEmail());
        utilisateurDTO.setDateNaissance(utilisateur.getDateNaissance());

        return utilisateurDTO;
    }

    static Utilisateur toEntity(UtilisateurDTO utilisateurDTO) {
        if (utilisateurDTO == null) {
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDTO.getId());
        utilisateur.setNom(utilisateurDTO.getNom());
        utilisateur.setPrénom(utilisateurDTO.getPrénom());
        utilisateur.setEmail(utilisateurDTO.getEmail());
        utilisateur.setDateNaissance(utilisateurDTO.getDateNaissance());

        return utilisateur;
    }
}
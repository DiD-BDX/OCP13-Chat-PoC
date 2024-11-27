package com.openclassrooms.ycyw.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.openclassrooms.ycyw.dto.UtilisateurDTO;
import com.openclassrooms.ycyw.models.Utilisateur;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

    UtilisateurDTO toDTO(Utilisateur utilisateur);

    @Mapping(target = "motDePasse", ignore = true)
    Utilisateur toEntity(UtilisateurDTO utilisateurDTO);
}
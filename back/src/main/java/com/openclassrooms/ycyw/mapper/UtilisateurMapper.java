package com.openclassrooms.ycyw.mapper;

import com.openclassrooms.ycyw.dto.UtilisateurDTO;
import com.openclassrooms.ycyw.models.Utilisateur;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {
    UtilisateurDTO toDto(Utilisateur utilisateur);
    Utilisateur toEntity(UtilisateurDTO utilisateurDTO);
}

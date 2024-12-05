package com.openclassrooms.ycyw.services;

import com.openclassrooms.ycyw.models.Utilisateur;
import com.openclassrooms.ycyw.repository.UtilisateurRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService {

    private static final Logger logger = LoggerFactory.getLogger(UtilisateurService.class);
    
    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur findById(Integer id) {
        logger.info("---------------UtilisateurServiceJAVA : Attempting to find user by ID: {}", id);
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        return utilisateur.orElseThrow(() -> {
            logger.error("---------------UtilisateurServiceJAVA : User not found with ID: {}", id);
            return new RuntimeException("Utilisateur non trouvé");
        });
    }

    public Utilisateur save(Utilisateur utilisateur) {
        logger.info("---------------UtilisateurServiceJAVA : Attempting to save user: {}", utilisateur);
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteById(Integer id) {
        logger.info("---------------UtilisateurServiceJAVA : Attempting to delete user by ID: {}", id);
        utilisateurRepository.deleteById(id);
    }

    public Utilisateur getCurrentUser() {
        logger.info("---------------UtilisateurServiceJAVA : Attempting to get current authenticated user");
        // Utiliser Spring Security pour obtenir l'utilisateur authentifié
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        logger.info("---------------UtilisateurServiceJAVA : Current authenticated user email: {}", email);
        // Rechercher l'utilisateur par email
        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.error("---------------UtilisateurServiceJAVA : User not found with email: {}", email);
                    return new RuntimeException("Utilisateur non trouvé");
                });
    }
}
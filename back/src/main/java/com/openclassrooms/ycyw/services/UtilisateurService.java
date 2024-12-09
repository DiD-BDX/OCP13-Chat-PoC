package com.openclassrooms.ycyw.services;

import com.openclassrooms.ycyw.models.Utilisateur;
import com.openclassrooms.ycyw.repository.UtilisateurRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service pour gérer les opérations liées aux utilisateurs.
 */
@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    /**
     * Trouve un utilisateur par son identifiant.
     *
     * @param id l'identifiant de l'utilisateur
     * @return l'utilisateur trouvé
     * @throws RuntimeException si l'utilisateur n'est pas trouvé
     */
    public Utilisateur findById(Integer id) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        return utilisateur.orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    /**
     * Sauvegarde un utilisateur.
     *
     * @param utilisateur l'utilisateur à sauvegarder
     * @return l'utilisateur sauvegardé
     */
    public Utilisateur save(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    /**
     * Supprime un utilisateur par son identifiant.
     *
     * @param id l'identifiant de l'utilisateur à supprimer
     */
    public void deleteById(Integer id) {
        utilisateurRepository.deleteById(id);
    }

    /**
     * Obtient l'utilisateur actuellement authentifié.
     *
     * @return l'utilisateur actuellement authentifié
     * @throws RuntimeException si l'utilisateur n'est pas trouvé
     */
    public Utilisateur getCurrentUser() {
        // Utiliser Spring Security pour obtenir l'utilisateur authentifié
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        // Rechercher l'utilisateur par email
        return utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }
}
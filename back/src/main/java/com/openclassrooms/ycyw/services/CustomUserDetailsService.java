package com.openclassrooms.ycyw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.openclassrooms.ycyw.models.Utilisateur;
import com.openclassrooms.ycyw.repository.UtilisateurRepository;

/**
 * Service personnalisé pour charger les détails de l'utilisateur.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    /**
     * Charge les détails de l'utilisateur par son adresse email.
     *
     * @param email l'adresse email de l'utilisateur
     * @return les détails de l'utilisateur
     * @throws UsernameNotFoundException si l'utilisateur n'est pas trouvé
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'email : " + email));

        return User.builder()
                .username(utilisateur.getEmail())
                .password(utilisateur.getMotDePasse())
                .roles("USER") // Attribuer un rôle par défaut
                .build();
    }
}
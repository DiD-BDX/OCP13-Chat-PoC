package com.openclassrooms.ycyw.services;

import com.openclassrooms.ycyw.models.Utilisateur;
import com.openclassrooms.ycyw.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public Utilisateur findById(Integer id) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(id);
        return utilisateur.orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    public Utilisateur save(Utilisateur utilisateur) {
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteById(Integer id) {
        utilisateurRepository.deleteById(id);
    }
}
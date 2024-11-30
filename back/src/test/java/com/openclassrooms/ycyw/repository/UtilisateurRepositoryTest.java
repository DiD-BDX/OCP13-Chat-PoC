package com.openclassrooms.ycyw.repository;

import com.openclassrooms.ycyw.models.Utilisateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UtilisateurRepositoryTest {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    public void testSaveAndFindUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom("John");
        utilisateur.setPrénom("Doe");
        utilisateur.setEmail("john.doe@example.com");
        utilisateur.setMotDePasse("password");
        utilisateur.setDateNaissance(LocalDate.of(1990, 1, 1));

        // Save the utilisateur
        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
        assertNotNull(savedUtilisateur);
        assertNotNull(savedUtilisateur.getId());

        // Find the utilisateur by ID
        Utilisateur foundUtilisateur = utilisateurRepository.findById(savedUtilisateur.getId()).orElse(null);
        assertNotNull(foundUtilisateur);
        assertEquals(savedUtilisateur.getId(), foundUtilisateur.getId());
        assertEquals("John", foundUtilisateur.getNom());
        assertEquals("Doe", foundUtilisateur.getPrénom());
        assertEquals("john.doe@example.com", foundUtilisateur.getEmail());
    }

    @Test
    public void testFindAllUtilisateurs() {
        Utilisateur utilisateur1 = new Utilisateur();
        utilisateur1.setNom("John");
        utilisateur1.setPrénom("Doe");
        utilisateur1.setEmail("john.doe@example.com");
        utilisateur1.setMotDePasse("password");
        utilisateur1.setDateNaissance(LocalDate.of(1990, 1, 1));

        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setNom("Jane");
        utilisateur2.setPrénom("Doe");
        utilisateur2.setEmail("jane.doe@example.com");
        utilisateur2.setMotDePasse("password");
        utilisateur2.setDateNaissance(LocalDate.of(1992, 2, 2));

        // Save the utilisateurs
        utilisateurRepository.save(utilisateur1);
        utilisateurRepository.save(utilisateur2);

        // Find all utilisateurs
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        assertNotNull(utilisateurs);
        assertEquals(2, utilisateurs.size());
    }
}
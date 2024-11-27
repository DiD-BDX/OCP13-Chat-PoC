package com.openclassrooms.ycyw.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.context.ActiveProfiles;

import com.openclassrooms.ycyw.models.Utilisateur;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class UtilisateurRepositoryTest {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    public void testSaveAndFindUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setNom("John Doe");
        utilisateur.setEmail("john.doe@example.com");
        utilisateur.setMotDePasse("password");
        utilisateur.setDateInscription(LocalDateTime.now());

        // Save the utilisateur
        Utilisateur savedUtilisateur = utilisateurRepository.save(utilisateur);
        assertNotNull(savedUtilisateur);
        assertNotNull(savedUtilisateur.getId());

        // Find the utilisateur by ID
        Utilisateur foundUtilisateur = utilisateurRepository.findById(savedUtilisateur.getId()).orElse(null);
        assertNotNull(foundUtilisateur);
        assertEquals(savedUtilisateur.getId(), foundUtilisateur.getId());
        assertEquals("John Doe", foundUtilisateur.getNom());
        assertEquals("john.doe@example.com", foundUtilisateur.getEmail());
    }

    @Test
    public void testFindAllUtilisateurs() {
        Utilisateur utilisateur1 = new Utilisateur();
        utilisateur1.setNom("John Doe");
        utilisateur1.setEmail("john.doe@example.com");
        utilisateur1.setMotDePasse("password");
        utilisateur1.setDateInscription(LocalDateTime.now());

        Utilisateur utilisateur2 = new Utilisateur();
        utilisateur2.setNom("Jane Doe");
        utilisateur2.setEmail("jane.doe@example.com");
        utilisateur2.setMotDePasse("password");
        utilisateur2.setDateInscription(LocalDateTime.now());

        // Save the utilisateurs
        utilisateurRepository.save(utilisateur1);
        utilisateurRepository.save(utilisateur2);

        // Find all utilisateurs
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        assertNotNull(utilisateurs);
        assertEquals(2, utilisateurs.size());
    }
}
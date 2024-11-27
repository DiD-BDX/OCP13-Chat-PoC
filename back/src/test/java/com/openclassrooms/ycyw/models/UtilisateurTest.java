package com.openclassrooms.ycyw.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class UtilisateurTest {

    @Test
    public void testUtilisateurConstructorAndGetters() {
        Long id = 1L;
        String nom = "John Doe";
        String email = "john.doe@example.com";
        String motDePasse = "password";
        LocalDateTime dateInscription = LocalDateTime.now();

        Utilisateur utilisateur = new Utilisateur(id, nom, email, motDePasse, dateInscription);

        assertEquals(id, utilisateur.getId());
        assertEquals(nom, utilisateur.getNom());
        assertEquals(email, utilisateur.getEmail());
        assertEquals(motDePasse, utilisateur.getMotDePasse());
        assertEquals(dateInscription, utilisateur.getDateInscription());
    }

    @Test
    public void testUtilisateurSetters() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L);
        utilisateur.setNom("John Doe");
        utilisateur.setEmail("john.doe@example.com");
        utilisateur.setMotDePasse("password");
        utilisateur.setDateInscription(LocalDateTime.now());

        assertNotNull(utilisateur.getId());
        assertNotNull(utilisateur.getNom());
        assertNotNull(utilisateur.getEmail());
        assertNotNull(utilisateur.getMotDePasse());
        assertNotNull(utilisateur.getDateInscription());
    }
}
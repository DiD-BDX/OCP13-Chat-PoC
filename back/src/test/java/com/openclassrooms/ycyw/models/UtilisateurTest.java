package com.openclassrooms.ycyw.models;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class UtilisateurTest {

    @Test
    public void testUtilisateurGettersAndSetters() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1);
        utilisateur.setNom("John");
        utilisateur.setPrénom("Doe");
        utilisateur.setEmail("john.doe@example.com");
        utilisateur.setMotDePasse("password");
        utilisateur.setDateNaissance(LocalDate.of(1990, 1, 1));

        assertEquals(1, utilisateur.getId());
        assertEquals("John", utilisateur.getNom());
        assertEquals("Doe", utilisateur.getPrénom());
        assertEquals("john.doe@example.com", utilisateur.getEmail());
        assertEquals("password", utilisateur.getMotDePasse());
        assertEquals(LocalDate.of(1990, 1, 1), utilisateur.getDateNaissance());
    }

    @Test
    public void testUtilisateurSetters() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1);
        utilisateur.setNom("John");
        utilisateur.setPrénom("Doe");
        utilisateur.setEmail("john.doe@example.com");
        utilisateur.setMotDePasse("password");
        utilisateur.setDateNaissance(LocalDate.of(1990, 1, 1));

        assertNotNull(utilisateur.getId());
        assertNotNull(utilisateur.getNom());
        assertNotNull(utilisateur.getPrénom());
        assertNotNull(utilisateur.getEmail());
        assertNotNull(utilisateur.getMotDePasse());
        assertNotNull(utilisateur.getDateNaissance());
    }
}
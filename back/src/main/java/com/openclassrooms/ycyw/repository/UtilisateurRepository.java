package com.openclassrooms.ycyw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.ycyw.models.Utilisateur;

/**
 * Répository pour l'entité Utilisateur.
 */
@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

    /**
     * Trouve un utilisateur par son adresse email.
     *
     * @param email l'adresse email de l'utilisateur
     * @return un Optional contenant l'utilisateur correspondant à l'adresse email, ou vide s'il n'y en a pas
     */
    Optional<Utilisateur> findByEmail(String email);
}
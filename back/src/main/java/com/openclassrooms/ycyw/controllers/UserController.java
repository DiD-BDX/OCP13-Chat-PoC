package com.openclassrooms.ycyw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.openclassrooms.ycyw.models.Utilisateur;
import com.openclassrooms.ycyw.services.ChatService;
import com.openclassrooms.ycyw.services.UtilisateurService;

@RestController
public class UserController {

    private final UtilisateurService utilisateurService;
    private final ChatService chatService;

    @Autowired
    public UserController(UtilisateurService utilisateurService, ChatService chatService) {
        this.utilisateurService = utilisateurService;
        this.chatService = chatService;
    }

    /**
     * Point d'entrée pour la connexion.
     * Cette méthode peut être vide, car l'authentification est gérée par Spring Security.
     */
    @PostMapping("/api/login")
    public void login() {
        // Méthode vide
    }

    /**
     * Récupère l'ID de l'utilisateur actuel.
     *
     * @return l'ID de l'utilisateur actuel
     */
    @GetMapping("/api/user/id")
    public int getUserId() {
        Utilisateur currentUser = utilisateurService.getCurrentUser();
        return currentUser.getId();
    }

    /**
     * Récupère un utilisateur par son ID.
     *
     * @param id l'ID de l'utilisateur
     * @return l'utilisateur correspondant à l'ID
     */
    @GetMapping("/api/user/{id}")
    public Utilisateur getUserById(@PathVariable int id) {
        return utilisateurService.findById(id);
    }

    /**
     * Récupère ou crée l'ID de la conversation pour l'utilisateur actuel.
     *
     * @return l'ID de la conversation
     */
    @GetMapping("/api/conversation/id")
    public int getConversationId() {
        Utilisateur currentUser = utilisateurService.getCurrentUser();
        return chatService.getOrCreateConversationIdByUserId(currentUser.getId());
    }
}
package com.openclassrooms.ycyw.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.ycyw.models.Utilisateur;
import com.openclassrooms.ycyw.services.ChatService;
import com.openclassrooms.ycyw.services.UtilisateurService;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private ChatService chatService;

    @PostMapping("/api/login")
    public void login() {
        logger.info("-------------UserControllerJAVA : Login endpoint hit");
        // Cette méthode peut être vide, car l'authentification est gérée par Spring Security
    }

    @GetMapping("/api/user/id")
    public int getUserId() {
        logger.info("-------------UserControllerJAVA : Attempting to get current user ID");
        Utilisateur currentUser = utilisateurService.getCurrentUser();
        logger.info("-------------UserControllerJAVA : Current user ID: {}", currentUser.getId());
        return currentUser.getId();
    }

    @GetMapping("/api/conversation/id")
    public int getConversationId() {
        logger.info("-------------UserControllerJAVA : Attempting to get conversation ID");
        Utilisateur currentUser = utilisateurService.getCurrentUser();
        logger.info("-------------UserControllerJAVA : Current user ID: {}", currentUser.getId());
        return chatService.getOrCreateConversationIdByUserId(currentUser.getId());
    }
}
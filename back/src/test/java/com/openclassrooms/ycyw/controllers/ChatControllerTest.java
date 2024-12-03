package com.openclassrooms.ycyw.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.openclassrooms.ycyw.dto.ChatDTO;
import com.openclassrooms.ycyw.models.Chat;
import com.openclassrooms.ycyw.models.Utilisateur;
import com.openclassrooms.ycyw.services.ChatService;
import com.openclassrooms.ycyw.services.UtilisateurService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ChatControllerTest {

    @Mock
    private ChatService chatService;

    @Mock
    private UtilisateurService utilisateurService;

    @InjectMocks
    private ChatController chatController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSendMessage() {
        // Arrange
        ChatDTO chatDTO = new ChatDTO();
        chatDTO.setSenderId(1);
        chatDTO.setContent("Hello");

        Utilisateur sender = new Utilisateur();
        sender.setId(1);

        Chat chat = new Chat();
        chat.setContent("Hello");
        chat.setSender(sender);

        Chat savedChat = new Chat();
        savedChat.setContent("Hello");
        savedChat.setSender(sender);

        when(utilisateurService.findById(1)).thenReturn(sender);
        when(chatService.save(any(Chat.class))).thenReturn(savedChat);

        // Act
        ChatDTO result = chatController.sendMessage(chatDTO);

        // Assert
        assertNotNull(result);
        assertEquals("Hello", result.getContent());
        verify(utilisateurService, times(1)).findById(1);
        verify(chatService, times(1)).save(any(Chat.class));
    }
}

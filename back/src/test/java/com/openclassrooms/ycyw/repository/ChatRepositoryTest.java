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

import com.openclassrooms.ycyw.models.Chat;
import com.openclassrooms.ycyw.models.Utilisateur;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.ANY)
public class ChatRepositoryTest {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    public void testSaveAndFindChat() {
        Utilisateur sender = new Utilisateur();
        sender.setId(1L);
        sender.setNom("John Doe");
        sender.setEmail("john.doe@example.com");
        sender.setMotDePasse("password");
        sender.setDateInscription(LocalDateTime.now());

        // Save the sender
        Utilisateur savedSender = utilisateurRepository.save(sender);

        Chat chat = new Chat();
        chat.setSender(savedSender);
        chat.setContent("Hello, World!");
        chat.setTimestamp(LocalDateTime.now());
        chat.setStatus("sent");

        // Save the chat
        Chat savedChat = chatRepository.save(chat);
        assertNotNull(savedChat);
        assertNotNull(savedChat.getId());

        // Find the chat by ID
        Chat foundChat = chatRepository.findById(savedChat.getId()).orElse(null);
        assertNotNull(foundChat);
        assertEquals(savedChat.getId(), foundChat.getId());
        assertEquals("Hello, World!", foundChat.getContent());
    }

    @Test
    public void testFindAllChats() {
        Utilisateur sender = new Utilisateur();
        sender.setId(1L);
        sender.setNom("John Doe");
        sender.setEmail("john.doe@example.com");
        sender.setMotDePasse("password");
        sender.setDateInscription(LocalDateTime.now());

        // Save the sender
        Utilisateur savedSender = utilisateurRepository.save(sender);

        Chat chat1 = new Chat();
        chat1.setSender(savedSender);
        chat1.setContent("Hello, World!");
        chat1.setTimestamp(LocalDateTime.now());
        chat1.setStatus("sent");

        Chat chat2 = new Chat();
        chat2.setSender(savedSender);
        chat2.setContent("Hi again!");
        chat2.setTimestamp(LocalDateTime.now());
        chat2.setStatus("received");

        // Save the chats
        chatRepository.save(chat1);
        chatRepository.save(chat2);

        // Find all chats
        List<Chat> chats = chatRepository.findAll();
        assertNotNull(chats);
        assertEquals(2, chats.size());
    }
}
package com.openclassrooms.ycyw.repository;

import com.openclassrooms.ycyw.models.Chat;
import com.openclassrooms.ycyw.models.Utilisateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ChatRepositoryTest {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Test
    public void testSaveAndFindAllChats() {
        Utilisateur sender = new Utilisateur();
        sender.setNom("John Doe");
        sender.setEmail("john.doe@example.com");
        sender.setMotDePasse("password");
        sender.setDateNaissance(LocalDateTime.now().toLocalDate());

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
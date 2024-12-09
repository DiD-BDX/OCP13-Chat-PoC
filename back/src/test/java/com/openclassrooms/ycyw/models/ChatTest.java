package com.openclassrooms.ycyw.models;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class ChatTest {

    @Test
    public void testChatConstructorAndGetters() {
        Utilisateur sender = new Utilisateur();
        sender.setId(1);
        sender.setNom("John Doe");

        String content = "Hello, World!";
        LocalDateTime timestamp = LocalDateTime.now();
        String status = "sent";

        Chat chat = new Chat();
        chat.setId(1);
        chat.setSender(sender);
        chat.setContent(content);
        chat.setTimestamp(timestamp);
        chat.setStatus(status);

        assertEquals(1, chat.getId());
        assertEquals(sender, chat.getSender());
        assertEquals(content, chat.getContent());
        assertEquals(timestamp, chat.getTimestamp());
        assertEquals(status, chat.getStatus());
    }

    @Test
    public void testChatSetters() {
        Chat chat = new Chat();
        Utilisateur sender = new Utilisateur();
        sender.setId(1);
        sender.setNom("John Doe");

        chat.setId(1);
        chat.setSender(sender);
        chat.setConversationId(1);
        chat.setContent("Hello, World!");
        chat.setTimestamp(LocalDateTime.now());
        chat.setStatus("sent");

        assertNotNull(chat.getId());
        assertNotNull(chat.getSender());
        assertNotNull(chat.getConversationId());
        assertNotNull(chat.getContent());
        assertNotNull(chat.getTimestamp());
        assertNotNull(chat.getStatus());
    }
}
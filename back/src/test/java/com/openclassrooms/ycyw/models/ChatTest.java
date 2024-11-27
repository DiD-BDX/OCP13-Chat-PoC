package com.openclassrooms.ycyw.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class ChatTest {

    @Test
    public void testChatConstructorAndGetters() {
        Utilisateur sender = new Utilisateur();
        sender.setId(1L);
        sender.setNom("John Doe");

        String content = "Hello, World!";
        LocalDateTime timestamp = LocalDateTime.now();
        String status = "sent";

        Chat chat = new Chat(1L, sender, content, timestamp, status);

        assertEquals(1L, chat.getId());
        assertEquals(sender, chat.getSender());
        assertEquals(content, chat.getContent());
        assertEquals(timestamp, chat.getTimestamp());
        assertEquals(status, chat.getStatus());
    }

    @Test
    public void testChatSetters() {
        Chat chat = new Chat();
        chat.setId(1L);

        Utilisateur sender = new Utilisateur();
        sender.setId(1L);
        sender.setNom("John Doe");

        chat.setSender(sender);
        chat.setContent("Hello, World!");
        chat.setTimestamp(LocalDateTime.now());
        chat.setStatus("sent");

        assertNotNull(chat.getId());
        assertNotNull(chat.getSender());
        assertNotNull(chat.getContent());
        assertNotNull(chat.getTimestamp());
        assertNotNull(chat.getStatus());
    }
}
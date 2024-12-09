package com.openclassrooms.ycyw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.openclassrooms.ycyw.models.Chat;

/**
 * Répository pour l'entité Chat.
 */
@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {

    /**
     * Trouve le dernier message envoyé par un utilisateur donné.
     *
     * @param senderId l'identifiant de l'expéditeur
     * @return un Optional contenant le dernier message envoyé par l'utilisateur, ou vide s'il n'y en a pas
     */
    Optional<Chat> findFirstBySenderIdOrderByTimestampDesc(Integer senderId);

    /**
     * Trouve l'identifiant de la conversation le plus élevé.
     *
     * @return un Optional contenant l'identifiant de la conversation le plus élevé, ou vide s'il n'y en a pas
     */
    @Query("SELECT MAX(c.conversationId) FROM Chat c")
    Optional<Integer> findMaxConversationId();
}
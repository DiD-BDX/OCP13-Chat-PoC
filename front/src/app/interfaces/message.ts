/**
 * Interface représentant un message dans une conversation.
 * @interface
 */
export interface Message {
    /**
     * Identifiant de l'expéditeur du message.
     * @type {number}
     */
    senderId: number;

    /**
     * Identifiant de la conversation à laquelle le message appartient.
     * @type {number}
     */
    conversationId: number;

    /**
     * Contenu du message.
     * @type {string}
     */
    content: string;

    /**
     * Horodatage du message.
     * @type {string}
     */
    timestamp: string;

    /**
     * Statut du message (par exemple, envoyé, reçu, lu).
     * @type {string}
     */
    status: string;

    /**
     * Nom de l'expéditeur du message (optionnel).
     * @type {string}
     */
    senderName?: string;

    /**
     * Prénom de l'expéditeur du message (optionnel).
     * @type {string}
     */
    senderSurname?: string;
}
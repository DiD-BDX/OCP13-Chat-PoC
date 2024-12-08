export interface Message {
    id: number;
    senderId: number;
    conversationId: number;
    content: string;
    timestamp: string;
    status: string;
    senderName?: string;
    senderSurname?: string;
}
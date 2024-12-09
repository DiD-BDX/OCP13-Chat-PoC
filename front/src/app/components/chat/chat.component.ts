import { Component, OnInit, ViewChild, ElementRef, AfterViewChecked } from '@angular/core';
import { WebsocketService } from '../../services/websocket.service';
import { UserService } from '../../services/user.service';
import { Message } from '../../interfaces/message';
import { Router } from '@angular/router';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit, AfterViewChecked {

  @ViewChild('messagesContainer') private messagesContainer!: ElementRef;

  messages: Message[] = [];
  newMessage: string = '';
  userId: number = 0;
  conversationId: number = 0;

  constructor(private websocketService: WebsocketService, private userService: UserService, private router: Router) {}

  /**
   * Initialise le composant de chat
   * - Charge les messages stockés depuis le session storage
   * - Récupère les identifiants de l'utilisateur
   * - Récupère l'ID de l'utilisateur et l'ID de la conversation en utilisant les identifiants
   * - S'abonne au service WebSocket pour recevoir les messages
   */
  ngOnInit(): void {
    // Charge les messages stockés depuis le session storage
    const storedMessages = sessionStorage.getItem('chatMessages');
    if (storedMessages) {
      this.messages = JSON.parse(storedMessages);
    }
  
    // Récupère les identifiants de l'utilisateur
    const credentials = this.userService.getCredentials();
    
    if (credentials) {
      const email = credentials.email;
      const password = credentials.password;
      if (email && password) {
        // Récupère l'ID de l'utilisateur en utilisant les identifiants
        this.userService.getUserId(email, password).subscribe({
          next: (id: number) => {
            this.userId = id;
          },
          error: (error: any) => console.error('Échec de la récupération de l\'ID utilisateur', error)
        });
        // Récupère l'ID de la conversation en utilisant les identifiants
        this.userService.getConversationId(email, password).subscribe({
          next: (id: number) => this.conversationId = id,
          error: (error: any) => console.error('Échec de la récupération de l\'ID de la conversation', error)
        });
      } else {
        console.error('Aucun identifiant trouvé');
      }
    } else {
      console.error('Les identifiants sont nuls');
    }
  
    // S'abonne au service WebSocket pour recevoir les messages
    this.websocketService.watch('/topic/public').subscribe({
      next: (message: any) => {
        const parsedMessage: Message = JSON.parse(message.body);
        parsedMessage.timestamp = this.formatTimestamp(parsedMessage.timestamp);
        // Récupère les détails de l'utilisateur pour l'expéditeur du message
        this.userService.getUserById(parsedMessage.senderId).subscribe({
          next: (user: { nom: string, prénom: string }) => {
            parsedMessage.senderName = user.nom;
            parsedMessage.senderSurname = user.prénom;
            this.messages.push(parsedMessage);
            this.updateSessionStorage();
          },
          error: (error: any) => console.error('Échec de la récupération des informations de l\'utilisateur', error)
        });
      },
      error: (error: any) => console.error('Échec de la réception du message', error)
    });
  }

  /**
   * Envoie un nouveau message
   */
  sendMessage(): void {
    if (this.newMessage.trim()) {
      const message: Message = {
        senderId: this.userId,
        conversationId: this.conversationId,
        content: this.newMessage,
        timestamp: new Date().toISOString(),
        status: 'sent'
      };
      this.websocketService.publish('/app/chat.sendMessage', message);
      this.newMessage = '';
      this.scrollToBottom();
    }
  }

  /**
   * Formate le timestamp pour qu'il soit lisible
   * @param timestamp Le timestamp original
   * @returns Le timestamp formaté
   */
  private formatTimestamp(timestamp: string): string {
    const date = new Date(timestamp);
    const day = date.getDate().toString().padStart(2, '0');
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const hours = date.getHours().toString().padStart(2, '0');
    const minutes = date.getMinutes().toString().padStart(2, '0');
    return `${day}/${month} ${hours}:${minutes}`;
  }

  /**
   * Fait défiler jusqu'en bas du conteneur de messages après la vérification de la vue
   */
  ngAfterViewChecked(): void {
    this.scrollToBottom();
  }

  /**
   * Fait défiler jusqu'en bas du conteneur de messages
   */
  private scrollToBottom(): void {
    try {
      this.messagesContainer.nativeElement.scrollTop = this.messagesContainer.nativeElement.scrollHeight;
    } catch (err) {
      console.error('Échec du défilement jusqu\'en bas', err);
    }
  }

  /**
   * Met à jour le session storage avec les messages actuels
   */
  private updateSessionStorage(): void {
    sessionStorage.setItem('chatMessages', JSON.stringify(this.messages));
  }

  /**
   * Déconnecte l'utilisateur et navigue vers la page de connexion
   */
  logout(): void {
    this.userService.logout();
    this.router.navigate(['/login']);
  }
}
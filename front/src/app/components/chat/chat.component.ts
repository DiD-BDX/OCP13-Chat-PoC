import { Component, OnInit, ViewChild, ElementRef, AfterViewChecked } from '@angular/core';
import { WebsocketService } from '../../services/websocket.service';
import { UserService } from '../../services/user.service';
import { Message } from '../../interfaces/message';

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

  constructor(private websocketService: WebsocketService, private userService: UserService) {}

  ngOnInit(): void {
    console.log('----------chatComponentTS : Chat component initialized');
    const credentials = this.userService.getCredentials();
    if (credentials) {
      console.log('----------chatComponentTS : Credentials found:', credentials);
      this.userService.getUserId(credentials.email, credentials.password).subscribe({
        next: (id) => {
          console.log('----------chatComponentTS : User ID fetched:', id);
          this.userId = id;
        },
        error: (error) => console.error('----------chatComponentTS : Failed to get user ID', error)
      });
      this.userService.getConversationId(credentials.email, credentials.password).subscribe({
        next: (id) => this.conversationId = id,
        error: (error) => console.error('----------chatComponentTS : Failed to get conversation ID', error)
      });
    } else {
      console.error('----------chatComponentTS : No credentials found');
    }

    this.websocketService.watch('/topic/public').subscribe({
      next: (message) => {
        console.log('----------chatComponentTS : Received message:', message.body);
        const parsedMessage: Message = JSON.parse(message.body);
        parsedMessage.timestamp = this.formatTimestamp(parsedMessage.timestamp);
        this.userService.getUserById(parsedMessage.senderId).subscribe({
          next: (user) => {
            parsedMessage.senderName = user.nom;
            parsedMessage.senderSurname = user.prénom;
            this.messages.push(parsedMessage);
          },
          error: (error) => console.error('----------chatComponentTS : Failed to get user info', error)
        });
      },
      error: (error) => console.error('----------chatComponentTS : Failed to receive message', error)
    });
  }

  sendMessage(): void {
    if (this.newMessage.trim()) {
      const message = {
        senderId: this.userId,
        conversationId: this.conversationId,
        content: this.newMessage,
        timestamp: new Date().toISOString(),
        status: 'sent'
      };
      console.log('----------chatComponentTS : Sending message:', message);
      this.websocketService.publish('/app/chat.sendMessage', message);
      this.newMessage = '';
      this.scrollToBottom();
    }
  }

  private formatTimestamp(timestamp: string): string {
    const date = new Date(timestamp);
    const day = date.getDate().toString().padStart(2, '0');
    const month = (date.getMonth() + 1).toString().padStart(2, '0');
    const hours = date.getHours().toString().padStart(2, '0');
    const minutes = date.getMinutes().toString().padStart(2, '0');
    return `${day}/${month} ${hours}:${minutes}`;
  }

  ngAfterViewChecked(): void {
    this.scrollToBottom();
  }
  
  private scrollToBottom(): void {
    try {
      this.messagesContainer.nativeElement.scrollTop = this.messagesContainer.nativeElement.scrollHeight;
    } catch (err) {
      console.error('----------chatComponentTS : Failed to scroll to bottom', err);
    }
  }
}
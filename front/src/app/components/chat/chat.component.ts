import { Component, OnInit } from '@angular/core';
import { WebsocketService } from '../../services/websocket.service';
import { UserService } from '../../services/user.service';
import { Message } from '../../interfaces/message';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit {
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
        const parsedMessage = JSON.parse(message.body);
        this.messages.push(parsedMessage);
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
    }
  }
}
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

  ngOnInit(): void {
    console.log('----------chatComponentTS : Chat component initialized');
    const storedMessages = sessionStorage.getItem('chatMessages');
    if (storedMessages) {
      this.messages = JSON.parse(storedMessages);
    }
  
    const credentials = this.userService.getCredentials();
    
    if (credentials) {
      const email = credentials.email;
      const password = credentials.password;
      if (email && password) {
        console.log('----------chatComponentTS : Credentials found:', { email, password });
        this.userService.getUserId(email, password).subscribe({
          next: (id) => {
            console.log('----------chatComponentTS : User ID fetched:', id);
            this.userId = id;
          },
          error: (error) => console.error('----------chatComponentTS : Failed to get user ID', error)
        });
        this.userService.getConversationId(email, password).subscribe({
          next: (id) => this.conversationId = id,
          error: (error) => console.error('----------chatComponentTS : Failed to get conversation ID', error)
        });
      } else {
        console.error('----------chatComponentTS : No credentials found');
      }
    } else {
      console.error('----------chatComponentTS : Credentials are null');
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
            this.updateSessionStorage();
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
      //this.messages.push(message);
      //this.updateSessionStorage();
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

  private updateSessionStorage(): void {
    sessionStorage.setItem('chatMessages', JSON.stringify(this.messages));
  }

  logout(): void {
    this.userService.logout();
    this.router.navigate(['/login']);
  }
}
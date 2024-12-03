import { Component, OnInit } from '@angular/core';
import { WebsocketService } from '../../services/websocket.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit {
  messages: string[] = [];
  newMessage: string = '';

  constructor(private websocketService: WebsocketService) {}

  ngOnInit(): void {
    this.websocketService.watch('/topic/public').subscribe((message) => {
      this.messages.push(message.body);
    });
  }

  sendMessage(): void {
    if (this.newMessage.trim()) {
      this.websocketService.publish('/app/chat.sendMessage', this.newMessage);
      this.newMessage = '';
    }
  }
}
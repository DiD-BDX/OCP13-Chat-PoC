import { Injectable } from '@angular/core';
import { RxStomp } from '@stomp/rx-stomp';
import { RxStompConfig } from '@stomp/rx-stomp/esm6';
import { Observable } from 'rxjs';
import { WebSocketMessage } from '../interfaces/websocket-message';

/**
 * Service WebSocket pour gérer les connexions et les communications via WebSocket.
 * @class
 */
@Injectable({
  providedIn: 'root'
})
export class WebsocketService {
  private rxStomp: RxStomp;

  constructor() {
    const rxStompConfig: RxStompConfig = {
      webSocketFactory: () => new WebSocket('ws://localhost:8080/ws'),
      heartbeatIncoming: 0,
      heartbeatOutgoing: 20000,
      reconnectDelay: 200,
      debug: (msg: string): void => {
        console.log(new Date(), msg);
      }
    };

    this.rxStomp = new RxStomp();
    this.rxStomp.configure(rxStompConfig);
    this.rxStomp.activate();
  }

  /**
   * Écoute les messages sur un sujet spécifique.
   * @param {string} topic - Le sujet à écouter.
   * @returns {Observable<WebSocketMessage>} - Un observable des messages reçus.
   */
  public watch(topic: string): Observable<WebSocketMessage> {
    return new Observable((observer) => {
      this.rxStomp.watch(topic).subscribe({
        next: (message) => {
          observer.next({ body: message.body });
        },
        error: (err) => observer.error(err),
        complete: () => observer.complete()
      });
    });
  }

  /**
   * Publie un message sur une destination spécifique.
   * @param {string} destination - La destination du message.
   * @param {object} body - Le corps du message.
   */
  public publish(destination: string, body: object): void {
    const message = JSON.stringify(body);
    this.rxStomp.publish({ destination, body: message });
  }
}
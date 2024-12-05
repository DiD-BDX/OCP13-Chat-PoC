import { Injectable } from '@angular/core';
import { RxStomp } from '@stomp/rx-stomp';
import { RxStompConfig } from '@stomp/rx-stomp/esm6';
import { Observable } from 'rxjs';

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

  public watch(topic: string): Observable<any> {
    return new Observable((observer) => {
      this.rxStomp.watch(topic).subscribe({
        next: (message) => {
          console.log('---------------WebsocketServiceTS : Received message:', message.body);
          observer.next(message);
        },
        error: (err) => observer.error(err),
        complete: () => observer.complete()
      });
    });
  }

  public publish(destination: string, body: any): void {
    const message = JSON.stringify(body);
    console.log('---------------WebsocketServiceTS : Sending message:', message);
    this.rxStomp.publish({ destination, body: message });
  }
}
import { Injectable } from '@angular/core';
import { RxStomp } from '@stomp/rx-stomp';
import { RxStompConfig } from '@stomp/rx-stomp';

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

  public watch(topic: string) {
    return this.rxStomp.watch(topic);
  }

  public publish(destination: string, body: string) {
    this.rxStomp.publish({ destination, body });
  }
}
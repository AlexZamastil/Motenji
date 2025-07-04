import { Injectable } from '@angular/core';
import { Client, Message, StompSubscription } from '@stomp/stompjs';
import SockJS from 'sockjs-client';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private stompClient: Client;
  private messageSubject = new Subject<any>();
  private subscription?: StompSubscription;

  constructor() {
    this.stompClient = new Client({
      webSocketFactory: () => new SockJS('http://localhost:8080/ws'),
      reconnectDelay: 5000,
    });
    this.connect()
  }

  connect() {
    this.stompClient.onConnect = () => {
      this.subscription = this.stompClient.subscribe('/topic/chat', (message: Message) => {
        const body = JSON.parse(message.body)
        this.messageSubject.next(body)
      })
    }
    this.stompClient.onStompError = (frame) => {
      console.error('Broker reported error: ' + frame.headers['message'])
      console.error('Details: ' + frame.body)
    }
    this.stompClient.activate()
    console.log("Connected to Websocket")
  }

  getMessages() {
    return this.messageSubject.asObservable();
  }

  sendMessage(message: any) {
    if (this.stompClient.connected) {
      this.stompClient.publish({
        destination: '/app/chat',
        body: JSON.stringify(message)
      });
      console.log('Sending a chat message: ' + JSON.stringify(message));
    } else {
      console.warn('Cannot send a message, client not connected');
    }
  }

  closeConnection() {
    this.subscription?.unsubscribe()
    this.stompClient.deactivate().then(r => console.log(r));
  }
}

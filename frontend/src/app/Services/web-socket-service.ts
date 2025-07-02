// web-socket-service.ts
import { Injectable } from '@angular/core';
import { Client, Message, over } from 'stompjs';
import SockJS from 'sockjs-client';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private stompClient: Client | null = null;
  private messageSubject = new Subject<any>();

  connect() {
    const socket = new SockJS('http://localhost:8080/ws'); // ✅ backend endpoint
    this.stompClient = over(socket);

    this.stompClient.connect({}, () => {
      this.stompClient?.subscribe('/topic/chat', (message: Message) => {
        const body = JSON.parse(message.body);
        this.messageSubject.next(body);
      });
    });
  }

  getMessages() {
    return this.messageSubject.asObservable();
  }

  sendMessage(message: { message: string; username: string }) {
    if (this.stompClient?.connected) {
      this.stompClient.send('/app/chat', {}, JSON.stringify(message));
    }
  }

  closeConnection() {
    this.stompClient?.disconnect(() => {}, {});
  }
}

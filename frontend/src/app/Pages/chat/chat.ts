import {Component, OnDestroy, OnInit} from '@angular/core';
import {WebSocketService} from '../../Services/web-socket-service';
import {Subscription} from 'rxjs';
import {FormsModule} from '@angular/forms';
import {MatInput} from '@angular/material/input';

@Component({
  selector: 'app-chat',
  imports: [
    FormsModule,
    MatInput
  ],
  templateUrl: './chat.html',
  styleUrl: './chat.css'
})
export class Chat implements OnInit, OnDestroy {
  private messageSubscription?: Subscription;
  protected text: string = '';
  protected from: string = '';
  protected messages: { username: string; message: string }[] = [];
  constructor(private webSocketService: WebSocketService) {
  }

  ngOnInit() {
    this.messageSubscription = this.webSocketService.getMessages().subscribe((msg) => {
      // handle incoming messages here if needed
      console.log('Received:', msg);
    });
  }
  sendMessage() {
    const message = {
      type: 'message',
      data: {
        message: this.text,
        username: this.from,
      }
    };
    this.webSocketService.sendMessage(message);
    this.text = '';
  }
  ngOnDestroy() {
    this.messageSubscription?.unsubscribe();
    this.webSocketService.closeConnection()
  }
}

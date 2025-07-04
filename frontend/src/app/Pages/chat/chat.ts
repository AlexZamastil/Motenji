import {Component, OnDestroy, OnInit} from '@angular/core';
import {WebSocketService} from '../../Services/web-socket-service';
import {Subscription} from 'rxjs';
import {FormsModule} from '@angular/forms';
import {MatInput} from '@angular/material/input';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule,
    MatInput
  ],
  templateUrl: './chat.html',
  styleUrl: './chat.css'
})
export class Chat implements OnInit, OnDestroy {
  private messageSubscription?: Subscription;
  protected text: string = '';
  protected from: string = '';
  protected userChatColor: string;
  protected messages: { username: string; message: string; color: string; time: string }[] = [];

  constructor(private webSocketService: WebSocketService) {
  }

  ngOnInit() {
    this.messageSubscription = this.webSocketService.getMessages().subscribe((message) => {
      this.messages.push({username: message.username, message: message.message, color: message.color, time: message.time});
      console.log('Received:', message);
    });
    this.userChatColor = this.generateUserColor()
  }

  sendMessage() {
    const message = {
      message: this.text,
      username: this.from,
      color: this.userChatColor,
      time : this.getCurrentTime()
    };
    this.webSocketService.sendMessage(message);
    this.text = '';
  }

  ngOnDestroy() {
    this.messageSubscription?.unsubscribe();
    this.webSocketService.closeConnection()
  }

  generateUserColor(): string {
    const r = Math.floor(Math.random() * 106) + 150;
    const g = Math.floor(Math.random() * 106) + 150;
    const b = Math.floor(Math.random() * 106) + 150;
    return `rgb(${r}, ${g}, ${b})`;
  }
  getCurrentTime(): string {
    let time = new Date();
    let hours = time.getHours();
    let minutes = time.getMinutes();
    return `${hours}:${minutes}`;
  }
}

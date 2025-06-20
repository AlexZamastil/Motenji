import { Component } from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatButton} from '@angular/material/button';
import {MatInput} from '@angular/material/input';
import {CallAPI} from '../../Services/call-api';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [
    FormsModule,
    MatButton,
    MatInput,
    ReactiveFormsModule
  ],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {
  constructor(private api: CallAPI, private router : Router) { }
  nickname = ''
  password = ''
  login() {
    if (this.nickname.trim() === '' || this.password.trim() === '') {
      console.error('Please enter nickname and password');
      return
    }
    console.log('trying to login user: ' + this.nickname);
    this.api.POST("user/login", {"username": this.nickname, "password": this.password})
      .subscribe({
        next: result => {
          console.log(result);
          let splitData = result.data.split("token: ")
          let token = splitData[1];
          console.log(token);
          localStorage.setItem("token", token);
          this.router.navigate(['/profile']);
        }, error: e => {
          console.log(e);
          if (e.status === 401) {
            console.error('Wrong username or password');
          }
        }
      })
  }
}

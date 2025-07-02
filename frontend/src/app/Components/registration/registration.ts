import { Component } from '@angular/core'
import {MatFormFieldModule} from '@angular/material/form-field'
import {MatInputModule} from '@angular/material/input'
import {MatSelectModule} from '@angular/material/select'
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';
import { FormsModule } from '@angular/forms';
import {CallAPI} from '../../Services/call-api';

@Component({
  selector: 'app-registration',
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, MatSelectModule, MatButtonModule, MatDividerModule, MatIconModule, FormsModule],
  templateUrl: './registration.html',
  styleUrl: './registration.css'
})
export class Registration {
      nickname = ''
      password = ''
      passwordConfirm = ''
      constructor(private api: CallAPI) {
      }
      register() {
        if (this.nickname.trim() === '' || this.password.trim() === '' || this.passwordConfirm.trim() === '') {
          console.error('Please enter nickname and password');
          return
        }
        if (this.password != this.passwordConfirm) {
          console.error('Passwords don\'t match')
          return
        }
        console.log('trying to register user: ' + this.nickname)
        this.api.POST("user/register", {"username": this.nickname, "password": this.password, "passwordConfirm": this.passwordConfirm}).subscribe(
          result => {console.log(result);}
        )
      }
}

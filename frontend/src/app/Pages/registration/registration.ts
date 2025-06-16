import { Component } from '@angular/core'
import {MatFormFieldModule} from '@angular/material/form-field'
import {MatInputModule} from '@angular/material/input'
import {MatSelectModule} from '@angular/material/select'
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';
import { FormsModule } from '@angular/forms';

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

      register() {
        console.log('trying to register user: ' + this.nickname + ' ' + this.password)

      }
}

import { Component } from '@angular/core';
import {MatButton} from '@angular/material/button';
import {Router} from '@angular/router';

@Component({
  selector: 'app-profile',
  imports: [
    MatButton
  ],
  templateUrl: './profile.html',
  styleUrl: './profile.css'
})
export class Profile {
          constructor(private router: Router) {
          }
          logout(){
          localStorage.removeItem('token')
          this.router.navigate(['/'])
        }
}

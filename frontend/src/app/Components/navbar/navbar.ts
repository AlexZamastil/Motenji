import { Component } from '@angular/core';
import {RouterModule} from '@angular/router';

@Component({
  selector: 'app-navbar',
  imports: [RouterModule],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css'
})
export class Navbar {
  constructor() {}
      isLoggedIn(): boolean {
      return (localStorage.getItem("userID") != null && localStorage.getItem("token") != null);
    }
}

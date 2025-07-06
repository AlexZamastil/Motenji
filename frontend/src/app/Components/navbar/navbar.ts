import {Component} from '@angular/core';
import {RouterModule} from '@angular/router';
import {UserData} from '../../Services/user-data';

@Component({
  selector: 'app-navbar',
  imports: [RouterModule],
  standalone: true,
  templateUrl: './navbar.html',
  styleUrl: './navbar.css'
})
export class Navbar {
  constructor(public userdata: UserData) {

  }
}

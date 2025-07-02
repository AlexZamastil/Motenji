import { Component } from '@angular/core';
import {Login} from '../../Components/login/login';
import {Registration} from '../../Components/registration/registration';

@Component({
  selector: 'app-sign-in',
  imports: [
    Login,
    Registration
  ],
  templateUrl: './sign-in.html',
  styleUrl: './sign-in.css'
})
export class SignIn {

}

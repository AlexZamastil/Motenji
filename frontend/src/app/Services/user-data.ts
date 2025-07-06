import {effect, Injectable, signal} from '@angular/core';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserData {

  constructor( private router : Router) {
    effect(() => {
      console.log(`Logged in value is ${this.loggedIn()}`);
    });
  }

  userID:number | null;
  jwtToken: string | null;
  loggedIn = signal(localStorage.getItem("userID") != null && localStorage.getItem("token") != null)

  login(loginMessage: string){
    console.log(loginMessage);
    let splitData = loginMessage.split(" X ")
    let token = splitData[0];
    let userID = splitData[1];
    console.log("TOKEN: " + token);
    console.log("USER ID: " + userID);
    this.userID = +userID;
    this.jwtToken = token;
    this.loggedIn.set(true)
    localStorage.setItem("token", token);
    localStorage.setItem("userID", userID);
    console.log("Logged in")
    this.router.navigate(['/profile']);
  }
  logout(){

    this.userID = null
    this.jwtToken = null
    this.loggedIn.set(false)
    localStorage.removeItem("token");
    localStorage.removeItem("userID");
    console.log("Logged out")
    this.router.navigate(['/'])
  }
}

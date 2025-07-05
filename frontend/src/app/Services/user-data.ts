import {Injectable} from '@angular/core';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserData {

  constructor( private router : Router) {
  }

  userID:number | null;
  jwtToken: string | null;

  login(loginMessage: string){
    console.log(loginMessage);
    let splitData = loginMessage.split(" X ")
    let token = splitData[0];
    let userID = splitData[1];
    console.log("TOKEN: " + token);
    console.log("USER ID: " + userID);
    this.userID = +userID;
    this.jwtToken = token;
    localStorage.setItem("token", token);
    localStorage.setItem("userID", userID);
    this.router.navigate(['/profile']);
  }
  logout(){
    this.userID = null
    this.jwtToken = null
    localStorage.removeItem("token");
    localStorage.removeItem("userID");
    this.router.navigate(['/'])
  }
}

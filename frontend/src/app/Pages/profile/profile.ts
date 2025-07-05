import {Component} from '@angular/core';
import {MatButton} from '@angular/material/button';
import {Router} from '@angular/router';
import {CallAPI} from '../../Services/call-api';
import {UserData} from '../../Services/user-data';

@Component({
  selector: 'app-profile',
  imports: [
    MatButton
  ],
  templateUrl: './profile.html',
  styleUrl: './profile.css'
})
export class Profile {
  constructor(private router: Router, private callAPI: CallAPI, private userData: UserData) {
  }
  userDetails:any

  queryUserData(){
    if (this.userData != null){
      console.log("QUERY USER DATA");
      this.callAPI.GET("user/getDetails/"+localStorage.getItem("userID")).subscribe(data=>{
        this.userDetails = data;
        console.log(this.userDetails);
      })
    }
  }
  ngOnInit() {
    this.queryUserData()
  }
  logout() {
    this.userData.logout()
  }
}

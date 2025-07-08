import {Component} from '@angular/core';
import {MatButton} from '@angular/material/button';
import {Router} from '@angular/router';
import {CallAPI} from '../../Services/call-api';
import {UserData} from '../../Services/user-data';


@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    MatButton
  ],
  templateUrl: './profile.html',
  styleUrl: './profile.css'
})
export class Profile {
  constructor(private router: Router, private callAPI: CallAPI, private userData: UserData) {}
  userDetails:any = null
  goalsDetails:any = null
  public hasGoals:boolean = false
  queryUserData(){
      console.log("QUERY USER DATA");
      this.callAPI.GET("user/getDetails/"+localStorage.getItem("userID")).subscribe(data=>{
        this.userDetails = data.data;
        console.log(this.userDetails);
      })
      console.log("QUERY GOAL DATA");
      this.callAPI.GET("goal/getUserGoals/"+localStorage.getItem("userID")).subscribe(data=> {
        if(data.data.length > 0 ){
          this.hasGoals = true;
          this.goalsDetails = data.data;
        } else {
          this.goalsDetails = "You don't have any active goals yet";
        }
        console.log(this.goalsDetails);
      })
  }
  createGoalRedirect(){
    this.router.navigate(['/createGoal']);
  }

  ngOnInit() {
    this.queryUserData()
  }
  logout() {
    this.userData.logout()
  }
}

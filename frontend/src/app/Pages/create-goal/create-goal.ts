import { Component } from '@angular/core';
import {CallAPI} from '../../Services/call-api';
import {Router} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

@Component({
  selector: 'app-create-goal',
  imports: [
    FormsModule,
    ReactiveFormsModule
  ],
  standalone: true,
  templateUrl: './create-goal.html',
  styleUrl: './create-goal.css'
})
export class CreateGoal {
  constructor(private api: CallAPI, private router: Router) {
    this.goalData = {
      name: '',
      measurable: false,
      deadline: '',
      progress: 0,
      userId: 0
    }
  }
  goalData: {
    name: string;
    measurable: boolean;
    deadline: string;
    progress: number;
    userId: number;
  }

  createGoal() {
    if (this.goalData.name.trim() === '' || this.goalData.deadline == null) {
      console.error('Please enter goal name and deadline');
      return
    }
    this.api.POST("goal/createGoal", {
      "name": this.goalData.name,
      "measurable": this.goalData.measurable,
      "deadline": this.goalData.deadline,
      "progress": this.goalData.progress,
      "userId": localStorage.getItem("userID")
    })
      .subscribe({
        next: result => {
          console.log("New goal added");
          console.log(result);
          this.router.navigate(['/profile']);
        }, error: e => {
          console.log(e);
        }
      })
  }
}

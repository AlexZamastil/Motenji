import {Routes} from '@angular/router';
import {Homepage} from './Pages/homepage/homepage';
import {Profile} from './Pages/profile/profile';
import {SignIn} from './Pages/sign-in/sign-in';

export const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: Homepage},
  {path: 'signIn', component: SignIn},
  {path: 'profile', component: Profile}
];

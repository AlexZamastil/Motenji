import {Routes} from '@angular/router';
import {Homepage} from './Pages/homepage/homepage';
import {Profile} from './Pages/profile/profile';
import {SignIn} from './Pages/sign-in/sign-in';
import {Chat} from './Pages/chat/chat';

export const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: Homepage},
  {path: 'signIn', component: SignIn},
  {path: 'profile', component: Profile},
  {path: 'chat', component: Chat},
];

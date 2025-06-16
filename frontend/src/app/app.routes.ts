import { Routes } from '@angular/router';
import { Homepage } from './Pages/homepage/homepage';
import { Registration } from './Pages/registration/registration';

export const routes: Routes = [
    {path: '', redirectTo: 'home', pathMatch: 'full'},
     {path: 'home', component: Homepage},
    {path:'register', component: Registration}
];

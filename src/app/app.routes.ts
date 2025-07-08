import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: 'login',
    loadComponent: () => import('./login/login.page').then((m) => m.LoginPage),
  },
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full',
  },
  {
    path: 'create-person',
    loadComponent: () => import('./create-person/create-person.page').then( m => m.CreatePersonPage)
  },
  {
    path: 'person-dashboard',
    loadComponent: () => import('./person-dashboard/person-dashboard.page').then( m => m.PersonDashboardPage)
  },
];

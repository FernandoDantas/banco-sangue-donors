import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NotAuthorizedComponent } from './core/not-authorized/not-authorized.component';
import { HomeComponent } from './home/home.component';
import { LayoutComponent } from './layout/layout.component';
import { AuthGuard } from './security/auth.guard';
import { LoginComponent } from './security/login/login.component';
import { NotFoundComponent } from './core/not-found/not-found.component';
import { ServerErrorComponent } from './core/server-error/server-error.component';
import { UsersComponent } from './users/users/users.component';
import { UserFormComponent } from './users/user-form/user-form.component';

const routes: Routes = [
  { path : 'login', component: LoginComponent},
    {
      path : '',
      component: LayoutComponent,
        children: [
      {
        path : 'home',
        data: {
          breadcrumb: 'Home'
        },
          component: HomeComponent,
          canActivate: [AuthGuard]
        },
      {
        path : 'users',
        data: {
          breadcrumb: 'Usuarios'
        },
        component: UsersComponent,
        canActivate: [AuthGuard]
      },
      {
        path : 'new-user',
        data: {
          breadcrumb: 'Novo usuario'
        },
        component: UserFormComponent,
        canActivate: [AuthGuard]
      },
      {
        path : '',
        redirectTo: '/home',
        pathMatch: 'full'
      },
  ]},


  { path : 'not-authorized', component: NotAuthorizedComponent},
  { path : '500', component: ServerErrorComponent},
  { path : '404', component: NotFoundComponent},
  { path: '**', component: NotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

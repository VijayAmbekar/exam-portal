import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './components/admin/dashboard/dashboard.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { UserDashboardComponent } from './components/user/user-dashboard/user-dashboard.component';
import { AdminGuard } from './services/admin.guard';
import { NormalGuard } from './services/normal.guard';

const routes: Routes = [
  {path:'', component: HomeComponent, pathMatch:'full'},
  {path:'signup', component: SignupComponent, pathMatch:'full'},
  {path:'login', component: LoginComponent, pathMatch:'full'},
  {path:'admin-dashboard', component: DashboardComponent, pathMatch:'full', canActivate: [AdminGuard]},
  {path:'user-dashboard', component: UserDashboardComponent, pathMatch:'full', canActivate: [NormalGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

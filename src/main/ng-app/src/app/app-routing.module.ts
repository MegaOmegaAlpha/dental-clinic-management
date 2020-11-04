import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AppointmentListComponent} from "./appointment/appointment-list/appointment-list.component";
import {AddVisitComponent} from "./visit/add-visit/add-visit.component";
import {LoginComponent} from "./login/login.component";

const routes: Routes = [
  {path: "appointments", component: AppointmentListComponent},
  {path: "create-visit", component: AddVisitComponent},
  {path: "login", component: LoginComponent},
  {path: "", component: LoginComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

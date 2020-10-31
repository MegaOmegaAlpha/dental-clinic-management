import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {AppointmentListComponent} from "./appointment/appointment-list/appointment-list.component";
import {AddVisitComponent} from "./visit/add-visit/add-visit.component";

const routes: Routes = [
  {path: "appointments", component: AppointmentListComponent},
  {path: "create-visit/:appointmentId", component: AddVisitComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

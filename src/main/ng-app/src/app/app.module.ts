import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppointmentListComponent } from './appointment/appointment-list/appointment-list.component';
import {HttpClientModule} from "@angular/common/http";
import { AddVisitComponent } from './visit/add-visit/add-visit.component';

@NgModule({
  declarations: [
    AppComponent,
    AppointmentListComponent,
    AddVisitComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

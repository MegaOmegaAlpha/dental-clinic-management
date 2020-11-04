import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AppointmentListComponent} from './appointment/appointment-list/appointment-list.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AddVisitComponent} from './visit/add-visit/add-visit.component';
import {FormsModule} from "@angular/forms";
import {MAT_DIALOG_DEFAULT_OPTIONS, MatDialogModule} from "@angular/material/dialog";
import {BrowserAnimationsModule, NoopAnimationsModule} from "@angular/platform-browser/animations";
import { AddVisitDialogComponent } from './visit/add-visit-dialog/add-visit-dialog.component';
import { LoginComponent } from './login/login.component';
import {HttpInterceptorService} from "./authentication/http-interceptor.service";

@NgModule({
  declarations: [
    AppComponent,
    AppointmentListComponent,
    AddVisitComponent,
    AddVisitDialogComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatDialogModule,
    FormsModule,
    BrowserAnimationsModule,
    NoopAnimationsModule  ],
  providers: [
    {provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: false}},
    {provide: HTTP_INTERCEPTORS, useClass: HttpInterceptorService, multi: true}
  ],
  bootstrap: [AppComponent],
  entryComponents: [
    AddVisitDialogComponent
  ],
})
export class AppModule {
}

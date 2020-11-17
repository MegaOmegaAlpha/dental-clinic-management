import {Component, OnInit} from '@angular/core';
import {Appointment} from "../appointment";
import {AppointmentService} from "../appointment.service";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {AddVisitDialogComponent} from "../../visit/add-visit-dialog/add-visit-dialog.component";
import {AuthenticationService} from "../../authentication/authentication.service";
import {MatButtonModule} from "@angular/material/button";
import {AppointmentEditComponent} from "../appointment-edit/appointment-edit.component";

@Component({
  selector: 'app-appointment-list',
  templateUrl: './appointment-list.component.html',
  styleUrls: ['./appointment-list.component.css']
})
export class AppointmentListComponent implements OnInit {

  appointments: Appointment[];

  constructor(private appointmentService: AppointmentService,
              private dialog: MatDialog,
              private authService: AuthenticationService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getAppointments();
  }

  private getAppointments(): void {
    this.appointmentService.getAppointments().subscribe(data => {
      this.appointments = data;
    }, error => {
      if (error.status == 401) {
        this.router.navigate(['']);
      }
    })
  }

  public takePatientFromAppointment(appointmentId: number): void {
    this.router.navigate(["/create-visit"], {queryParams: {apptmtId: appointmentId}});
  }

  public takePatientWithoutAppointment(): void {
    this.dialog.open(AddVisitDialogComponent, {width: '50', height: '50', hasBackdrop: true});
  }

  public updateAppointment(appointmentId: number): void {
    let dialogRef = this.dialog.open(AppointmentEditComponent, {hasBackdrop: true, data: {appointmentData: this.findAppointmentById(appointmentId)}});
    dialogRef.afterClosed().subscribe(result => {
      this.getAppointments();
    });
  }

  private findAppointmentById(id: number): Appointment {
    return this.appointments.filter(appointment => appointment.id == id)[0];
  }

  userLogout(): void {
    this.authService.logout();
    this.router.navigate(['']);
  }
}

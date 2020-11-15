import {Component, OnInit} from '@angular/core';
import {Appointment} from "../appointment";
import {AppointmentService} from "../appointment.service";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {AddVisitDialogComponent} from "../../visit/add-visit-dialog/add-visit-dialog.component";
import {AuthenticationService} from "../../authentication/authentication.service";
import {MatButtonModule} from "@angular/material/button";

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

  public cancelAppointment(appointmentId: number): void {
    this.appointmentService.cancelAppointment(appointmentId.toString()).subscribe(data => {
      console.log(data);
      this.getAppointments();
    });
  }

  userLogout(): void {
    this.authService.logout();
    this.router.navigate(['']);
  }
}

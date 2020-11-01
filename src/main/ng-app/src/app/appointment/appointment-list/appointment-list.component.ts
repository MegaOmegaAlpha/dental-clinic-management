import {Component, OnInit} from '@angular/core';
import {Appointment} from "../appointment";
import {AppointmentService} from "../appointment.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-appointment-list',
  templateUrl: './appointment-list.component.html',
  styleUrls: ['./appointment-list.component.css']
})
export class AppointmentListComponent implements OnInit {

  appointments: Appointment[];

  constructor(private appointmentService: AppointmentService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getAppointments();
  }

  private getAppointments(): void {
    this.appointmentService.getAppointments().subscribe(data => {
      this.appointments = data;
    })
  }

  public takePatientFromAppointment(appointmentId: number): void {
    /*let appointment = this.appointments.filter(aptmt => aptmt.id === appointmentId);
    let patient = appointment[0].patient;*/
    this.router.navigate(["/create-visit", appointmentId])
  }

  public cancelAppointment(appointmentId: number): void {
    this.appointmentService.cancelAppointment(appointmentId.toString()).subscribe(data => {
      console.log(data);
      this.getAppointments();
    });
  }

}

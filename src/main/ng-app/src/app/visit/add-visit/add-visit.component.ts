import { Component, OnInit } from '@angular/core';
import {Patient} from "../../patient/patient";
import {PatientService} from "../../patient/patient.service";
import {ActivatedRoute} from "@angular/router";
import {Appointment} from "../../appointment/appointment";
import {AppointmentService} from "../../appointment/appointment.service";

@Component({
  selector: 'app-add-visit',
  templateUrl: './add-visit.component.html',
  styleUrls: ['./add-visit.component.css']
})
export class AddVisitComponent implements OnInit {

  appointment: Appointment;

  constructor(private appointmentService: AppointmentService,
              private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    let appointmentId = this.route.snapshot.paramMap.get("appointmentId");
    this.fillAppointment(appointmentId)
  }

  private fillAppointment(id: string): void {
    this.appointmentService.getAppointment(id).subscribe(data => {
      this.appointment = data;
      console.log(this.appointment);
    });
  }

  public onSubmit() {

  }

}

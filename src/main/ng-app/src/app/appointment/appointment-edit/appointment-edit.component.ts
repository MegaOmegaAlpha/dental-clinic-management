import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Appointment} from "../appointment";
import {AppointmentService} from "../appointment.service";
import {DentistService} from "../../dentist/dentist.service";
import {Dentist} from "../../dentist/dentist";

@Component({
  selector: 'app-appointment-edit',
  templateUrl: './appointment-edit.component.html',
  styleUrls: ['./appointment-edit.component.css']
})
export class AppointmentEditComponent implements OnInit {

  appointment: Appointment;
  dentists: Dentist[];
  dentistId: number;
  isCurrentDoctorSelected: boolean = true;

  constructor(@Inject(MAT_DIALOG_DATA) public data: any,
              private appointmentService: AppointmentService,
              private dentistService: DentistService,
              private matDialogRef: MatDialogRef<AppointmentEditComponent>) { }

  ngOnInit(): void {
    this.appointment = this.data.appointmentData;
    this.fillDentists();
  }

  private fillDentists(): void {
    this.dentistService.getAllDentists().subscribe(data => this.dentists = data);
  }

  onSubmit(): void {
    if (!this.isCurrentDoctorSelected) {
      this.appointment.dentist = this.findDentistById(this.dentistId);
    }
    this.appointmentService.updateAppointment(this.appointment).subscribe(data => {
      console.log(data)
      this.matDialogRef.close(data);
    }, error => console.log(error))
  }

  private findDentistById(id: number): Dentist {
    return this.dentists.filter(dentist => dentist.id == id)[0];
  }

  cancelAppointment(): void {
    this.appointmentService.cancelAppointment(this.appointment.id).subscribe(data => {
      console.log(data);
      this.matDialogRef.close();
    });
  }

}

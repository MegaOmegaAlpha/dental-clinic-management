import { Component, OnInit } from '@angular/core';
import {AppointmentService} from "../appointment.service";
import {PatientService} from "../../patient/patient.service";
import {Appointment} from "../appointment";
import {Patient} from "../../patient/patient";
import {Dentist} from "../../dentist/dentist";
import {DentistService} from "../../dentist/dentist.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-appointment-add',
  templateUrl: './appointment-add.component.html',
  styleUrls: ['./appointment-add.component.css']
})
export class AppointmentAddComponent implements OnInit {

  appointment = new Appointment();
  dentists: Dentist[];
  dentistId: number;
  patient = new Patient();
  patients: Patient[];
  patientId: number;
  isRegistered: boolean;
  appointmentDate: Date;

  constructor(private appointmentService: AppointmentService,
              private patientService: PatientService,
              private dentistService: DentistService,
              private router: Router) { }

  ngOnInit(): void {
    this.fillPatients();
    this.fillDentists();
  }

  private fillPatients(): void {
    this.patientService.getAllPatients().subscribe(data => this.patients = data);
  }

  private fillDentists(): void {
    this.dentistService.getAllDentists().subscribe(data => this.dentists = data)
  }

  choosePatient(id: number): void {
    this.patient = this.patients.filter(patient => patient.id == id)[0];
  }

  updatePatient(): void {
    this.patient = new Patient();
  }

  private chooseDentist(id: number): Dentist {
    return this.dentists.filter(dentist => dentist.id == id)[0];
  }

  onSubmit(): void {
    if (!this.isRegistered) {
      this.patientService.savePatient(this.patient).subscribe(data => {
        console.log(data);
        this.appointment.patient = data;
        this.createAppointment();
      }, error => {
        console.log(error);
        switch (error.status) {
          case 500:
            alert("Some problem on the server side");
            break;
          case 409:
            alert("Пользователь с таким паспортом уже существует");
            document.getElementsByName("passport")[0].focus();
            break;
        }
      });
    } else {
      this.appointment.patient = this.patient;
      this.createAppointment();
    }
  }

  private createAppointment(): void {
    let date = new Date(this.appointmentDate);
    date = new Date(date.getTime() - date.getTimezoneOffset() * 60000)
    this.appointment.appointmentDate = date.toISOString().split("T")[0];
    this.appointment.dentist = this.chooseDentist(this.dentistId);
    this.appointmentService.createAppointment(this.appointment).subscribe(data => {
      console.log(data);
      this.router.navigate(['appointments']);
    });
  }

}

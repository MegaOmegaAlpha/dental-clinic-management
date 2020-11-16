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

  private chooseDentist(id: number): Dentist {
    return this.dentists.filter(dentist => dentist.id == id)[0];
  }

  onSubmit(): void {
    let date = new Date(this.appointmentDate);
    date = new Date(date.getTime() - date.getTimezoneOffset() * 60000)
    this.appointment.appointmentDate = date.toISOString().split("T")[0];
    this.appointment.dentist = this.chooseDentist(this.dentistId);
    this.appointment.patient = this.patient;

    this.appointmentService.createAppointment(this.appointment).subscribe(data => {
      console.log(data);
      this.router.navigate(['appointments']);
    })
  }

}

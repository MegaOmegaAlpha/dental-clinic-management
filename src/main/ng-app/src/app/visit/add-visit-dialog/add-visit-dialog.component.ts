import { Component, OnInit } from '@angular/core';
import {PatientService} from "../../patient/patient.service";
import {Patient} from "../../patient/patient";

@Component({
  selector: 'app-add-visit-dialog',
  templateUrl: './add-visit-dialog.component.html',
  styleUrls: ['./add-visit-dialog.component.css']
})
export class AddVisitDialogComponent implements OnInit {

  patient: Patient = new Patient();
  patients: Patient[];
  isRegistered: boolean;

  constructor(private patientService: PatientService) { }

  ngOnInit(): void {
    this.fillPatients();
  }

  private fillPatients(): void {
    this.patientService.getAllPatients().subscribe(data => this.patients = data);
  }

  choosePatient(patientId: string): void {
    this.patient = this.findPatientById(Number(patientId));
  }

  private findPatientById(id: number): Patient {
    return this.patients.filter(patient => patient.id == id)[0];
  }

}

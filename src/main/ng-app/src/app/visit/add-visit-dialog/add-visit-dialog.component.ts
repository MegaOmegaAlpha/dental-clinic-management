import { Component, OnInit } from '@angular/core';
import {PatientService} from "../../patient/patient.service";
import {Patient} from "../../patient/patient";
import {Router} from "@angular/router";
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-add-visit-dialog',
  templateUrl: './add-visit-dialog.component.html',
  styleUrls: ['./add-visit-dialog.component.css']
})
export class AddVisitDialogComponent implements OnInit {

  patient: Patient = new Patient();
  patients: Patient[];
  patientId: number;
  isRegistered: boolean;

  constructor(private patientService: PatientService,
              private dialogRef: MatDialogRef<AddVisitDialogComponent>,
              private router: Router) { }

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

  onSubmit(): void {
    if (this.isRegistered) {
      this.router.navigate(['create-visit'], {queryParams: {patient: this.patient.id}});
      this.dialogRef.close();
    } else {
      this.patientService.savePatient(this.patient).subscribe(data => {
        this.router.navigate(['create-visit'], {queryParams: {patient: data.id}});
        console.log(data);
        this.dialogRef.close();
      }, error => {
        console.log(error);
        switch (error.status) {
          case 500:
            alert("Some problem on the server side");
            break;
          case 409:
            alert("Пользователь с таким паспортом уже существует");
            document.getElementsByName("passport")[0].click();
            break;
        }
      });
    }
  }

  closeDialog(): void {
    this.dialogRef.close();
  }

}

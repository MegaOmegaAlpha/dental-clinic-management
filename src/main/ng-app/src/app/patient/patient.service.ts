import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Patient} from "./patient";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  private url = "http://localhost:8889/api/patients";

  constructor(private http: HttpClient) { }

  getPatient(patientId: string): Observable<Patient> {
    return this.http.get<Patient>(`${this.url}` + "/" + patientId);
  }

  getAllPatients(): Observable<Patient[]> {
    return this.http.get<Patient[]>(`${this.url}`)
  }

  savePatient(patient: Patient): Observable<Patient> {
    return this.http.post<Patient>(`${this.url}`, patient);
  }
}

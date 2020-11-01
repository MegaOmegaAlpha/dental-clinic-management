import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Appointment} from "./appointment";

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  private url = "http://localhost:8889/api/appointments";

  constructor(private httpClient: HttpClient) {
  }

  getAppointments(): Observable<Appointment[]> {
    return this.httpClient.get<Appointment[]>(`${this.url}`);
  }

  getAppointment(id: string): Observable<Appointment> {
    return this.httpClient.get<Appointment>(`${this.url}` + '/' + id);
  }

  cancelAppointment(id: string): Observable<any> {
    return this.httpClient.delete(`${this.url}` + '/' + id);
  }
}

import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Dentist} from "./dentist";

@Injectable({
  providedIn: 'root'
})
export class DentistService {

  private url = "http://localhost:8889/api/dentists";

  constructor(private http: HttpClient) { }

  getAllDentists(): Observable<Dentist[]> {
    return this.http.get<Dentist[]>(`${this.url}`);
  }
}

import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Diagnosis} from "./diagnosis";

@Injectable({
  providedIn: 'root'
})
export class DiagnosisService {

  private url = "http://localhost:8889/api/diagnosis";

  constructor(private http: HttpClient) { }

  getDiagnosisAll(): Observable<Diagnosis[]> {
    return this.http.get<Diagnosis[]>(`${this.url}`);
  }
}

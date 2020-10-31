import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Visit} from "./visit";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class VisitService {

  private url = "http://localhost:8889/api/visits";

  constructor(private http: HttpClient) { }

  createVisit(visit: Visit): Observable<Visit> {
    return this.http.post<Visit>(`${this.url}`, visit);
  }

}

import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Procedure} from "./procedure";

@Injectable({
  providedIn: 'root'
})
export class ProcedureService {

  private url = "http://localhost:8889/api/procedures";

  constructor(private http: HttpClient) { }

  public getProcedures(): Observable<Procedure[]> {
    return this.http.get<Procedure[]>(`${this.url}`);
  }

}

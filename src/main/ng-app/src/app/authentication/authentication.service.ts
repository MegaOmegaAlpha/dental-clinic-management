import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUsername';
  USER_PASSWORD_SESSION_ATTR_NAME = 'authenticatedPassword';

  private url = "http://localhost:8889/api/user";
  username;
  password;

  constructor(private http: HttpClient) {
  }

  authenticate(username: string, password: string): Observable<any> {
    return this.http.get<any>(`${this.url}`, {
      headers: {
        authorization: this.createBasicAuthToken(username, password)
      }
    }).pipe(map((res) => {
      this.username = username;
      this.password = password;
      this.registerLoginData(username, password);
    }));
  }

  private createBasicAuthToken(username: string, password: string): string {
    return 'Basic ' + window.btoa(username + ':' + password);
  }

  private registerLoginData(username: string, password: string): void {
    sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, username);
    sessionStorage.setItem(this.USER_PASSWORD_SESSION_ATTR_NAME, password);
  }

  isUserLoggedIn(): boolean {
    let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
    return user !== null;
  }

  getUsername(): string {
    return sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
  }

  getPassword(): string {
    return sessionStorage.getItem(this.USER_PASSWORD_SESSION_ATTR_NAME);
  }

  logout() {
    sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
    sessionStorage.removeItem(this.USER_PASSWORD_SESSION_ATTR_NAME);
    this.username = null;
    this.password = null;
  }

}

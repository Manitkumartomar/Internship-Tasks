import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthServiceService {
  baseUrl = 'http://localhost:8080/auth';

  constructor(private http: HttpClient) {}

  authSubject = new BehaviorSubject<any>({
    user: null
  });

  searchSubject:BehaviorSubject<string>=new BehaviorSubject<string>("");

  login(userData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/token`, userData, {
      responseType: 'text',
    });
  }

  register(userData: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/register`, userData, {
      responseType: 'text',
    });
  }

  getUserProfile(): Observable<any> {
    const token: any = localStorage.getItem('jwt');
    console.log("Token : ",JSON.parse(token));

    const headers = new HttpHeaders({
      Authorization: `Bearer ${JSON.parse(token)}`,
    });
    return this.http.get<any>(`${this.baseUrl}/user`, { headers }).pipe(
      tap((user) => {
        const currentState = this.authSubject.value;
        this.authSubject.next({ ...currentState, user });
      })
    );
  }

  logout() {
    localStorage.clear();
    this.authSubject.next({});
  }
}

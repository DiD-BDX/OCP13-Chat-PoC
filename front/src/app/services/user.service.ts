import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api'; // Remplacez par l'URL de votre API
  private credentialsKey = 'authCredentials';
  private credentials: { email: string, password: string } | null = null;
  
  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<void> {
    console.log('--------userServiceTS : Attempting login with email:', email, 'and password', password);
    const headers = new HttpHeaders({
      'Authorization': 'Basic ' + btoa(`${email}:${password}`)
    });
    return this.http.post<void>(`${this.apiUrl}/login`, {}, { headers });
  }

  getUserId(email: string, password: string): Observable<number> {
    console.log('--------userServiceTS : Fetching user ID for email:', email);
    const headers = new HttpHeaders({
      'Authorization': 'Basic ' + btoa(`${email}:${password}`)
    });
    return this.http.get<number>(`${this.apiUrl}/user/id`, { headers });
  }

  getUserById(userId: number): Observable<{ nom: string, prénom: string }> {
    const credentials = this.getCredentials();
    if (!credentials) {
      throw new Error('No credentials found');
    }
    const headers = new HttpHeaders({
      'Authorization': 'Basic ' + btoa(`${credentials.email}:${credentials.password}`)
    });
    return this.http.get<{ nom: string, prénom: string }>(`${this.apiUrl}/user/${userId}`, { headers });
  }

  getConversationId(email: string, password: string): Observable<number> {
    console.log('--------userServiceTS : Fetching conversation ID for email:', email);
    const headers = new HttpHeaders({
      'Authorization': 'Basic ' + btoa(`${email}:${password}`)
    });
    return this.http.get<number>(`${this.apiUrl}/conversation/id`, { headers });
  }

  setCredentials(email: string, password: string): void {
    console.log('--------userServiceTS : Setting credentials for email:', email);
    const credentials = { email, password };
    sessionStorage.setItem(this.credentialsKey, JSON.stringify(credentials));
  }

  getCredentials(): { email: string, password: string } | null {
    console.log('--------userServiceTS : Getting stored credentials');
    const credentials = sessionStorage.getItem(this.credentialsKey);
    return credentials ? JSON.parse(credentials) : null;
  }

  clearCredentials(): void {
    console.log('--------userServiceTS : Clearing stored credentials');
    sessionStorage.removeItem(this.credentialsKey);
  }

  logout(): void {
    console.log('--------userServiceTS : Logging out');
    this.clearCredentials();
    sessionStorage.clear(); // Vider le sessionStorage
  }
}
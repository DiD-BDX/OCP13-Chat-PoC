import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

/**
 * Service utilisateur pour gérer les opérations de connexion et de gestion des utilisateurs.
 * @class
 */
@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8080/api'; // l'URL de l'API
  private credentialsKey = 'authCredentials';
  private credentials: { email: string, password: string } | null = null;
  
  constructor(private http: HttpClient) {}

  /**
   * Effectue la connexion de l'utilisateur.
   * @param {string} email - L'email de l'utilisateur.
   * @param {string} password - Le mot de passe de l'utilisateur.
   * @returns {Observable<void>} - Un observable de la réponse de connexion.
   */
  login(email: string, password: string): Observable<void> {
    const headers = new HttpHeaders({
      'Authorization': 'Basic ' + btoa(`${email}:${password}`)
    });
    return this.http.post<void>(`${this.apiUrl}/login`, {}, { headers });
  }

  /**
   * Récupère l'identifiant de l'utilisateur.
   * @param {string} email - L'email de l'utilisateur.
   * @param {string} password - Le mot de passe de l'utilisateur.
   * @returns {Observable<number>} - Un observable de l'identifiant de l'utilisateur.
   */
  getUserId(email: string, password: string): Observable<number> {
    const headers = new HttpHeaders({
      'Authorization': 'Basic ' + btoa(`${email}:${password}`)
    });
    return this.http.get<number>(`${this.apiUrl}/user/id`, { headers });
  }

  /**
   * Récupère les informations de l'utilisateur par son identifiant.
   * @param {number} userId - L'identifiant de l'utilisateur.
   * @returns {Observable<{ nom: string, prénom: string }>} - Un observable des informations de l'utilisateur.
   */
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

  /**
   * Récupère l'identifiant de la conversation.
   * @param {string} email - L'email de l'utilisateur.
   * @param {string} password - Le mot de passe de l'utilisateur.
   * @returns {Observable<number>} - Un observable de l'identifiant de la conversation.
   */
  getConversationId(email: string, password: string): Observable<number> {
    const headers = new HttpHeaders({
      'Authorization': 'Basic ' + btoa(`${email}:${password}`)
    });
    return this.http.get<number>(`${this.apiUrl}/conversation/id`, { headers });
  }

  /**
   * Définit les informations d'identification de l'utilisateur.
   * @param {string} email - L'email de l'utilisateur.
   * @param {string} password - Le mot de passe de l'utilisateur.
   */
  setCredentials(email: string, password: string): void {
    const credentials = { email, password };
    sessionStorage.setItem(this.credentialsKey, JSON.stringify(credentials));
  }

  /**
   * Récupère les informations d'identification de l'utilisateur stockées.
   * @returns {{ email: string, password: string } | null} - Les informations d'identification de l'utilisateur ou null si non trouvées.
   */
  getCredentials(): { email: string, password: string } | null {
    const credentials = sessionStorage.getItem(this.credentialsKey);
    return credentials ? JSON.parse(credentials) : null;
  }

  /**
   * Efface les informations d'identification de l'utilisateur stockées.
   */
  clearCredentials(): void {
    sessionStorage.removeItem(this.credentialsKey);
  }

  /**
   * Déconnecte l'utilisateur en effaçant les informations d'identification et en vidant le sessionStorage.
   */
  logout(): void {
    this.clearCredentials();
    sessionStorage.clear(); // Vider le sessionStorage
  }
}
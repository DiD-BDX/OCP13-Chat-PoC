import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';

/**
 * Composant de connexion pour l'application.
 * @class
 */
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  /**
   * Crée une instance de LoginComponent.
   * @param {UserService} userService - Service utilisateur pour gérer les opérations de connexion.
   * @param {Router} router - Routeur Angular pour la navigation.
   */
  constructor(private userService: UserService, private router: Router) {}

  /**
   * Soumet le formulaire de connexion.
   * Appelle le service utilisateur pour effectuer la connexion et redirige vers la page de chat en cas de succès.
   */
  onSubmit(): void {
    this.userService.login(this.email, this.password).subscribe({
      next: () => {
        // Stocker les informations d'identification dans le service
        this.userService.setCredentials(this.email, this.password);
        sessionStorage.setItem('email', this.email);
        sessionStorage.setItem('password', this.password);
        // Rediriger vers la page de chat après une connexion réussie
        this.router.navigate(['/chat']);
      },
      error: (error: Error) => {
        console.error('Login failed', error);
      }
    });
  }
}
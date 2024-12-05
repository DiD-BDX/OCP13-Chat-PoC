import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  email: string = '';
  password: string = '';

  constructor(private userService: UserService, private router: Router) {}

  onSubmit(): void {
    console.log('------------loginComponentTS : Login form submitted with email:', this.email, 'and password', this.password);
    this.userService.login(this.email, this.password).subscribe({
      next: () => {
        console.log('------------loginComponentTS : Login successful');
        // Stocker les informations d'identification dans le service
        this.userService.setCredentials(this.email, this.password);
        // Rediriger vers la page de chat après une connexion réussie
        this.router.navigate(['/chat']);
      },
      error: (error) => {
        console.error('------------loginComponentTS : Login failed', error);
      }
    });
  }
}
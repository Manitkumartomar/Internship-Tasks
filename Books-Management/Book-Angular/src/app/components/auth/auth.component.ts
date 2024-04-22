import { Component } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { AuthServiceService } from '../../services/auth/auth-service.service';

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    ReactiveFormsModule,
  ],
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.css',
})
export class AuthComponent {
  isRegister = true;

  constructor(public authService: AuthServiceService) {}

  registerForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [
      Validators.required,
      Validators.minLength(6),
    ]),
    role: new FormControl('', [Validators.required]),
  });

  loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
  });

  handleRegister() {
    console.log('Registered', this.registerForm.value);
    this.authService.register(this.registerForm.value).subscribe({
      next: (response) => {
        localStorage.setItem('jwt', JSON.stringify(response));
        this.authService.getUserProfile().subscribe();
        console.log('SignUp success! ', response);
      },
    });
  }
  handleLogin() {
    console.log('Logged in', this.loginForm.value);
    this.authService.login(this.loginForm.value).subscribe({
      next: (response) => {
        localStorage.setItem('jwt', JSON.stringify(response));
        console.log('Login success! ', response);
        window.location.reload();
      },
      error: (error) => {
        console.log('error: ', error);
      },
    });
  }
  togglePanel() {
    this.isRegister = !this.isRegister;
  }
}

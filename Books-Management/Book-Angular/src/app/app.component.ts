import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './components/home/home.component';
import { AuthComponent } from './components/auth/auth.component';
import { AuthServiceService } from './services/auth/auth-service.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    NavbarComponent,
    FooterComponent,
    HomeComponent,
    AuthComponent,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'Book-Angular';

  constructor(public authService: AuthServiceService) {}

  user: any;

  ngOnInit() {
    this.authService.getUserProfile().subscribe({
      next: (auth) => (this.user = auth),
      error: (error) => console.log('Some Error occurred: ', error),
    });
    this.authService.authSubject.subscribe({
      next: (auth) => (this.user = auth.user),
      error: (error) => console.log('Some Error occurred: ', error),
    });
  }
}

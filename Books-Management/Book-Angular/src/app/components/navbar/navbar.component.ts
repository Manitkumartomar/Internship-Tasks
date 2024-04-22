import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AuthServiceService } from '../../services/auth/auth-service.service';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { debounceTime } from 'rxjs';
import { BookServiceService } from '../../services/book/book-service.service';
import { BookCardComponent } from '../book-card/book-card.component';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    MatToolbarModule,
    MatButtonModule,
    MatIconModule,

  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css',
})
export class NavbarComponent {
  constructor(
    public authService: AuthServiceService,
    private booksService: BookServiceService
  ) {}

  user: any = null;

  ngOnInit() {
    this.authService.authSubject.subscribe();
  }

  handleLogout() {
    this.authService.logout();
  }
}

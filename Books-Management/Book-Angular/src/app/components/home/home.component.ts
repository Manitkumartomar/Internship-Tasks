import { MatDialog } from '@angular/material/dialog';
import { Component } from '@angular/core';
import { BookCardComponent } from '../book-card/book-card.component';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { AddBookComponent } from '../add-book/add-book.component';
import { BookServiceService } from '../../services/book/book-service.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [BookCardComponent, MatIconModule, MatButtonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent {
  books = [];

  constructor(
    public dialog: MatDialog,
    private bookService: BookServiceService
  ) {}

  openAddBook() {
    this.dialog.open(AddBookComponent);
  }

  ngOnInit() {
    this.bookService.getBooks().subscribe();
    this.bookService.bookSubject.subscribe((state) => {
      this.books = state.books;
    });
  }
}

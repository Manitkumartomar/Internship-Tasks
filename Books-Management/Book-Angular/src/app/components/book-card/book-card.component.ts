import { Component, Input } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDialog } from '@angular/material/dialog';
import { MatIcon } from '@angular/material/icon';
import { EditBookComponent } from '../edit-book/edit-book.component';
import { BookServiceService } from '../../services/book/book-service.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-book-card',
  standalone: true,
  imports: [MatCardModule, MatButtonModule, MatIcon, CommonModule],
  templateUrl: './book-card.component.html',
  styleUrl: './book-card.component.css',
})
export class BookCardComponent {
  @Input() book: any;

  isFavorited:boolean = false;

  constructor(
    public dialog: MatDialog,
    private bookService: BookServiceService
  ) {}

  openEditBook() {
    this.dialog.open(EditBookComponent, { data: this.book });
  }

  handleDeleteBook() {
    this.bookService.deleteBook(this.book.id).subscribe();
  }

  onClickFavorite() {
    this.isFavorited = !this.isFavorited;
  }
}

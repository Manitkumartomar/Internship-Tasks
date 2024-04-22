import { Component } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatRadioModule } from '@angular/material/radio';
import { BookServiceService } from '../../services/book/book-service.service';

@Component({
  selector: 'app-add-book',
  standalone: true,
  imports: [
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatRadioModule,
  ],
  templateUrl: './add-book.component.html',
  styleUrl: './add-book.component.css',
})
export class AddBookComponent {
  books: any = {
    imgUrl: '',
    title: '',
    author: '',
    category: '',
  };

  constructor(private bookService: BookServiceService) {}

  onSubmit() {
    console.log('Added ...', this.books);
    this.bookService.addBooks(this.books).subscribe({
      next: (data) => console.log('Added ', data),
      error: (error) => console.log('Error ', error),
    });
  }
}

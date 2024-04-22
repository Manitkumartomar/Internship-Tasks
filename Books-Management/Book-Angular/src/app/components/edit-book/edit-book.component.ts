import { Component, Inject } from '@angular/core';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatRadioModule } from '@angular/material/radio';
import { BookServiceService } from '../../services/book/book-service.service';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

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
  templateUrl: './edit-book.component.html',
  styleUrl: './edit-book.component.css',
})
export class EditBookComponent {
  books: any = {
    imgUrl: '',
    title: '',
    author: '',
    category: '',
  };

  constructor(
    private bookService: BookServiceService,
    @Inject(MAT_DIALOG_DATA) public book: any
  ) {}

  onSubmit() {
    console.log('Edited...', this.books);
    this.bookService.updateBook(this.books).subscribe({
      next: (res) => {
        console.log('Updated Successfully!');
      },
      error: (error) => {
        console.log('Error occurred!', error);
      },
    });
  }

  ngOnInit() {
    this.books = this.book;
  }
}

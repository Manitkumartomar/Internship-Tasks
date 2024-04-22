import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class BookServiceService {
  baseUrl = 'http://localhost:8080/api/books';

  constructor(private http: HttpClient) {}

  bookSubject = new BehaviorSubject<any>({
    books: [],
    loading: false,
    newBook: null,
  });

  private getHeaders(): HttpHeaders {
    const token:any = localStorage.getItem('jwt');
    return new HttpHeaders({
      Authorization: `Bearer ${JSON.parse(token)}`,
    });
  }

  getBooks(): Observable<any> {
    const headers = this.getHeaders();
    return this.http.get(`${this.baseUrl}`, { headers }).pipe(
      tap((books) => {
        const currentState = this.bookSubject.value;
        this.bookSubject.next({ ...currentState, books });
      })
    );
  }

  addBooks(book: any): Observable<any> {
    const headers = this.getHeaders();
    return this.http.post(`${this.baseUrl}`, book, { headers }).pipe(
      tap((newBook) => {
        const currentState = this.bookSubject.value;
        this.bookSubject.next({
          ...currentState,
          books: [newBook, ...currentState.books],
        });
      })
    );
  }

  updateBook(book: any): Observable<any> {
    const headers = this.getHeaders();
    return this.http.put(`${this.baseUrl}/${book.id}`, book, { headers }).pipe(
      tap((updatedBook: any) => {
        const currentState = this.bookSubject.value;
        const updatedBooks = currentState.books.map((item: any) =>
          item.id === updatedBook.id ? updatedBook : item
        );
        this.bookSubject.next({
          ...currentState,
          books: updatedBooks,
        });
      })
    );
  }

  deleteBook(id: any): Observable<any> {
    const headers = this.getHeaders();
    return this.http.delete(`${this.baseUrl}/${id}`, { headers }).pipe(
      tap((deletedBook: any) => {
        const currentState = this.bookSubject.value;
        const updatedBooks = currentState.books.filter(
          (item: any) => item.id !== id
        );
        this.bookSubject.next({
          ...currentState,
          books: updatedBooks,
        });
      })
    );
  }
}

package com.example.lms.services;

import com.example.lms.entities.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getSingleBook(long id);

    Book updateBook(long id, Book book);

    void deleteBook(long id);

    Book addBook(Book book);
}

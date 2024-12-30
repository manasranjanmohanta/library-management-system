package com.example.lms.services;

import com.example.lms.entities.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {
    Page<Book> getAllBooks(int pageNo, int pageSize);

    Book getSingleBook(long id);

    Book updateBook(long id, Book book);

    void deleteBook(long id);

    Book addBook(Book book);
}

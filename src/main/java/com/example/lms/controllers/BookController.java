package com.example.lms.controllers;

import com.example.lms.entities.Book;
import com.example.lms.payload.response.PaginatedResponse;
import com.example.lms.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Add a new Book
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book createdBook = bookService.addBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    // Get a list of Books
    @GetMapping
    public ResponseEntity<PaginatedResponse<Book>> getAllBooks(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "10") int pageSize) {
        Page<Book> bookPage = bookService.getAllBooks(pageNo, pageSize);
        PaginatedResponse<Book> response = new PaginatedResponse<>(
                bookPage.getContent(),
                bookPage.getNumber(),
                bookPage.getSize(),
                bookPage.getTotalElements()
        );
        return ResponseEntity.ok(response);
    }

    // Get details of a specific Book
    @GetMapping("/{id}")
    public ResponseEntity<Book> getSingleBook(@PathVariable long id) {
        Book Book = bookService.getSingleBook(id);
        return ResponseEntity.ok(Book);
    }

    // Update Book details
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable long id, @RequestBody Book Book) {
        Book updatedBook = bookService.updateBook(id, Book);
        return ResponseEntity.ok(updatedBook);
    }

    // Delete an Book
    @DeleteMapping("/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
    
}

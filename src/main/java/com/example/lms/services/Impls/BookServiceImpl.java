package com.example.lms.services.Impls;

import com.example.lms.entities.Author;
import com.example.lms.entities.Book;
import com.example.lms.entities.Borrower;
import com.example.lms.exceptions.ResourceNotFoundException;
import com.example.lms.repositories.AuthorRepository;
import com.example.lms.repositories.BookRepository;
import com.example.lms.repositories.BorrowerRepository;
import com.example.lms.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BorrowerRepository borrowerRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, BorrowerRepository borrowerRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.borrowerRepository = borrowerRepository;
    }

    @Override
    public Page<Book> getAllBooks(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book getSingleBook(long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));
        return book;
    }

    @Override
    public Book updateBook(long id, Book book) {
        // Retrieve the existing book
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));

        // Update the book details
        existingBook.setTitle(book.getTitle());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setGenre(book.getGenre());

        // Check if the author exists
        if (book.getAuthor() != null) {
            Author author = authorRepository.findById(book.getAuthor().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));
            book.setAuthor(author);
            existingBook.setAuthor(author);
        }

        // Check if the borrower exists
        if (book.getBorrowedBy() != null) {
            Borrower borrower = borrowerRepository.findById(book.getBorrowedBy().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Borrower", "id", id));
            existingBook.setBorrowedBy(borrower);
            existingBook.setAvailable(false); // Mark as not available if borrowed
            existingBook.setBorrowedDate(book.getBorrowedDate());
            existingBook.setDueDate(book.getBorrowedDate().plusDays(14));
        } else {
            existingBook.setAvailable(true); // Mark as available if not borrowed
            existingBook.setBorrowedBy(null);
            existingBook.setBorrowedDate(null);
            existingBook.setDueDate(null);
        }
        return bookRepository.save(existingBook);
    }

    @Override
    public void deleteBook(long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));

        // Check if the book is currently borrowed
       if (!book.getAvailable()) {
           throw new IllegalStateException("Can not delete a book which is borrowed.");
       }
        bookRepository.deleteById(id);
    }

    @Override
    public Book addBook(Book book) {
        Author author = authorRepository.findById(book.getAuthor().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", book.getBorrowedBy().getId()));
        book.setAuthor(author);

        if (book.getBorrowedBy() != null) {
            Borrower borrower = borrowerRepository.findById(book.getBorrowedBy().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Borrower", "id", book.getBorrowedBy().getId()));
            book.setBorrowedBy(borrower);
            book.setAvailable(false);
            book.setBorrowedDate(LocalDate.now());
            book.setDueDate(LocalDate.now().plusDays(14));
        } else {
            book.setAvailable(true);
        }
        return bookRepository.save(book);
    }
}

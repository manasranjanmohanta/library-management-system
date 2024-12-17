package com.example.lms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 200, nullable = false)
    private String title;

    @Column(length = 20, nullable = false)
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Column(length = 50)
    private String genre;

    @Column(nullable = false)
    private Boolean available = true;

    @ManyToOne
    @JoinColumn(name = "borrowed_by")
    private Borrower borrowedBy;

    @Column
    private LocalDate borrowedDate;

    @Column
    private LocalDate dueDate;

    public Book() {
    }

    public Book(long id, String title, String isbn, Author author, String genre, Boolean available, Borrower borrowedBy, LocalDate borrowedDate, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
        this.available = available;
        this.borrowedBy = borrowedBy;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Borrower getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(Borrower borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    public LocalDate getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}

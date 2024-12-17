package com.example.lms.services;

import com.example.lms.entities.Author;

import java.util.List;

public interface AuthorService {
    Author addAuthor(Author author);

    List<Author> getAllAuthors();

    Author getSingleAuthor(long id);

    void deleteAuthor(long id);

    Author updateAuthor(long id, Author author);
}

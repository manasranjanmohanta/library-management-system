package com.example.lms.services;

import com.example.lms.entities.Author;
import org.springframework.data.domain.Page;

public interface AuthorService {
    Author addAuthor(Author author);

    Page<Author> getAllAuthors(int pageNo, int pageSize);

    Author getSingleAuthor(long id);

    void deleteAuthor(long id);

    Author updateAuthor(long id, Author author);
}

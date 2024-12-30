package com.example.lms.controllers;

import com.example.lms.entities.Author;
import com.example.lms.payload.response.PaginatedResponse;
import com.example.lms.services.AuthorService;
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
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // Add a new author
    @PostMapping
    public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
        Author createdAuthor = authorService.addAuthor(author);
        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
    }

    // Get a list of authors
    @GetMapping
    public ResponseEntity<PaginatedResponse<Author>> getAllAuthors(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize
    ) {
        Page<Author> authorPage = authorService.getAllAuthors(pageNo, pageSize);
        PaginatedResponse<Author> response = new PaginatedResponse<>(
                authorPage.getContent(),
                authorPage.getNumber(),
                authorPage.getSize(),
                authorPage.getTotalElements()
        );
        return ResponseEntity.ok(response);
    }

    // Get details of a specific author
    @GetMapping("/{id}")
    public ResponseEntity<Author> getSingleAuthor(@PathVariable long id) {
        Author author = authorService.getSingleAuthor(id);
        return ResponseEntity.ok(author);
    }

    // Update author details
    @PutMapping("/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable long id, @RequestBody Author author) {
        Author updatedAuthor = authorService.updateAuthor(id, author);
        return ResponseEntity.ok(updatedAuthor);
    }

    // Delete an author
    @DeleteMapping("/{id}")
    public ResponseEntity<Author> deleteAuthor(@PathVariable long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}

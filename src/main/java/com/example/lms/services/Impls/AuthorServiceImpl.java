package com.example.lms.services.Impls;

import com.example.lms.entities.Author;
import com.example.lms.exceptions.ResourceNotFoundException;
import com.example.lms.repositories.AuthorRepository;
import com.example.lms.services.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author addAuthor(Author author) {
        Author createdAuthor = authorRepository.save(author);
        return createdAuthor;
    }

    @Override
    public Page<Author> getAllAuthors(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return authorRepository.findAll(pageable);
    }

    @Override
    public Author getSingleAuthor(long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));
        return author;
    }

    @Override
    public void deleteAuthor(long id) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author", "id", id);
        }
        authorRepository.deleteById(id);
    }

    @Override
    public Author updateAuthor(long id, Author author) {
//        Author author = authorRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));
        Author updatedAuthor = authorRepository.findById(id).map(savedAuthor -> {
            savedAuthor.setName(author.getName());
            savedAuthor.setBooks(author.getBooks());
            savedAuthor.setEmail(author.getEmail());
            savedAuthor.setPhone(author.getPhone());
            return savedAuthor;
        }).orElseThrow(() -> new ResourceNotFoundException("Author", "id", id));
        return updatedAuthor;
    }
}

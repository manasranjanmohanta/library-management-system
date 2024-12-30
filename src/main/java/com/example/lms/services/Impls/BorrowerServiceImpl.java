package com.example.lms.services.Impls;

import com.example.lms.entities.Borrower;
import com.example.lms.exceptions.ResourceNotFoundException;
import com.example.lms.repositories.BorrowerRepository;
import com.example.lms.services.BorrowerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BorrowerServiceImpl implements BorrowerService {
    private final BorrowerRepository borrowerRepository;

    public BorrowerServiceImpl(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    @Override
    public Borrower addBorrower(Borrower borrower) {
        Borrower createdBorrower = borrowerRepository.save(borrower);
        return createdBorrower;
    }

    @Override
    public Page<Borrower> getAllBorrowers(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return borrowerRepository.findAll(pageable);
    }

    @Override
    public Borrower getSingleBorrower(long id) {
        Borrower borrower = borrowerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrower", "id", id));
        return borrower;
    }

    @Override
    public Borrower updateBorrower(long id, Borrower borrower) {
        Borrower updatedBorrower = borrowerRepository.findById(id).map(savedBorrower -> {
            savedBorrower.setName(borrower.getName());
            savedBorrower.setEmail(borrower.getEmail());
            savedBorrower.setPhone(borrower.getPhone());
            savedBorrower.setBooks(borrower.getBooks());
            return savedBorrower;
        }).orElseThrow(() -> new ResourceNotFoundException("Borrower", "id", id));
        return updatedBorrower;
    }

    @Override
    public void deleteBorrower(long id) {
        if ( !borrowerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Borrower", "id", id);
        }
        borrowerRepository.deleteById(id);
    }
}

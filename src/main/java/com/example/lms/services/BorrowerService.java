package com.example.lms.services;

import com.example.lms.entities.Borrower;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BorrowerService {
    Borrower addBorrower(Borrower borrower);

    Page<Borrower> getAllBorrowers(int pageNo, int pageSize);

    Borrower getSingleBorrower(long id);

    Borrower updateBorrower(long id, Borrower borrower);

    void deleteBorrower(long id);
}

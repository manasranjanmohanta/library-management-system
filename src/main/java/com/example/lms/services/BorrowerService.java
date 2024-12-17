package com.example.lms.services;

import com.example.lms.entities.Borrower;

import java.util.List;

public interface BorrowerService {
    Borrower addBorrower(Borrower borrower);

    List<Borrower> getAllBorrowers();

    Borrower getSingleBorrower(long id);

    Borrower updateBorrower(long id, Borrower borrower);

    void deleteBorrower(long id);
}

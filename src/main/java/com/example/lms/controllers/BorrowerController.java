package com.example.lms.controllers;

import com.example.lms.entities.Borrower;
import com.example.lms.payload.response.PaginatedResponse;
import com.example.lms.services.BorrowerService;
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
@RequestMapping("/api/borrowers")
public class BorrowerController {
    private final BorrowerService borrowerService;

    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    // Add a new borrower
    @PostMapping
    public ResponseEntity<Borrower> addBorrower(@RequestBody Borrower borrower) {
        Borrower createdBorrower = borrowerService.addBorrower(borrower);
        return new ResponseEntity<>(createdBorrower, HttpStatus.CREATED);
    }

    // Get a list of borrowers
    @GetMapping
    public ResponseEntity<PaginatedResponse<Borrower>> getAllBorrowers(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize
    ) {
        Page<Borrower> borrowerPage = borrowerService.getAllBorrowers(pageNo, pageSize);
        PaginatedResponse<Borrower> response = new PaginatedResponse<>(
                borrowerPage.getContent(),
                borrowerPage.getNumber(),
                borrowerPage.getSize(),
                borrowerPage.getTotalElements()
        );
        return ResponseEntity.ok(response);
    }

    // Get details of a specific borrower
    @GetMapping("/{id}")
    public ResponseEntity<Borrower> getSingleBorrower(@PathVariable long id) {
        Borrower borrower = borrowerService.getSingleBorrower(id);
        return ResponseEntity.ok(borrower);
    }

    // Update borrower details
    @PutMapping("/{id}")
    public ResponseEntity<Borrower> updateBorrower(@PathVariable long id, @RequestBody Borrower borrower) {
        Borrower updatedBorrower = borrowerService.updateBorrower(id, borrower);
        return ResponseEntity.ok(updatedBorrower);
    }

    // Delete an borrower
    @DeleteMapping("/{id}")
    public ResponseEntity<Borrower> deleteBorrower(@PathVariable long id) {
        borrowerService.deleteBorrower(id);
        return ResponseEntity.noContent().build();
    }
}

package com.eqMatrix;

import lombok.Data;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
class User {
    private String name;
    private Set<String> issuedBooks = new HashSet<>();
    private Map<String, BookDetails> libraryCard = new HashMap<>();

    @Data
    static class BookDetails {
        private LocalDate borrowedDate;
        private LocalDate returnedDate;
        private double fine;

        public BookDetails(LocalDate borrowedDate) {
            this.borrowedDate = borrowedDate;
        }

        public void calculateFine(double bookPrice) {
            LocalDate currentDate = LocalDate.now();
            long daysBorrowed = borrowedDate.until(currentDate).getDays();
            if (daysBorrowed > 90) {
                fine = (daysBorrowed - 90) * (0.02 * Double.parseDouble(String.valueOf(bookPrice)));
            }
        }
    }

    public User(String name) {
        this.name = name;
    }

    public void addIssuedBook(String isbn) {
        issuedBooks.add(isbn);
        BookDetails details = new BookDetails(LocalDate.now());
        libraryCard.put(isbn, details);
    }

    public void removeIssuedBook(String isbn) {
        issuedBooks.remove(isbn);
        libraryCard.get(isbn).setReturnedDate(LocalDate.now());
    }
}
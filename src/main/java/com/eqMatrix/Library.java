package com.eqMatrix;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
class Library {
    private List<Book> books = new ArrayList<>();
    private Set<String> uniqueIsbns = new HashSet<>();
    private List<User> users = new ArrayList<>();

//    public void addBook(Book book) {
//        books.add(book);
//    }
    public void addBook(Book book) throws DuplicateIsbnException {
        if (uniqueIsbns.contains(book.getIsbn())) {
            throw new DuplicateIsbnException("A book with the same ISBN already exists.");
        }
        books.add(book);
        uniqueIsbns.add(book.getIsbn());
    }

    public void searchByTitle(String title) {
        System.out.println("Searching for books by title: " + title);
        books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .forEach(book -> System.out.println("\nBook found Successfully\n"+"Book Title: " + book.getTitle() + ", Author: " + book.getAuthor()+"\n"));
    }

    public void searchByAuthor(String author) {
        System.out.println("Searching for books by author: " + author);
        books.stream()
                .filter(book -> book.getAuthor().equalsIgnoreCase(author))
                .forEach(book -> System.out.println("\nBook found Successfully\n"+"Book Title: " + book.getTitle() + ", Author: " + book.getAuthor()+"\n"));
    }

    public void searchByGenre(String genre) {
        System.out.println("Searching for books by genre: " + genre);
        books.stream()
                .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                .forEach(book -> System.out.println("\nBook found Successfully\n"+"Book Title: " + book.getTitle() + ", Genre: " + book.getGenre()+"\n"));
    }


    public void borrowBook(String isbn, User user) throws BookUnavailableException {
        Book book = findBookByISBN(isbn);
        if (book!=null && book.isAvailable()) {
            book.setAvailable(false);
            user.addIssuedBook(isbn);
        } else {
            throw new BookUnavailableException(isbn);
        }
    }

    public void returnBook(String isbn, User user) throws BookNotFoundException {
        Book book = findBookByISBN(isbn);
        if (book != null) {
            book.setAvailable(true);
            user.removeIssuedBook(isbn);
        } else {
            throw new BookNotFoundException(isbn);
        }
    }

    private Book findBookByISBN(String isbn) {
        return books.stream()
                .filter(book -> book.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);
    }

    public void displayAllBooks() {
        books.forEach(System.out::println);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void issuedToUser() {
        users.forEach(user -> {
            System.out.println("User: " + user.getName());
            System.out.println("Issued Books:");
            user.getIssuedBooks().forEach(isbn -> {
                Book book = findBookByISBN(isbn);
                System.out.println(book.getTitle() + " (ISBN: " + book.getIsbn() + ")");
            });
            //System.out.println("Library Card:");
            user.getLibraryCard().forEach((isbn, details) -> {
                System.out.println("\n----List of Borrowed Books----\n");
                System.out.println("ISBN: " + isbn);
                System.out.println("Book Borrowed On: " + details.getBorrowedDate());
                System.out.println("Book Returned On: " + details.getReturnedDate());
                System.out.println("Fine: " + details.getFine());
            });
            System.out.println("----------------------------\n");
        });
    }
}

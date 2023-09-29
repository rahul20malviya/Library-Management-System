package com.eqMatrix;


public class BookNotFoundException extends Throwable {
    public BookNotFoundException(String isbn) {
        super("Book with ISBN '" + isbn + "' was not found in the library.");
    }
}

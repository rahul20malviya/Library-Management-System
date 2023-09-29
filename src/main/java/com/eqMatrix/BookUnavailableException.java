package com.eqMatrix;

public class BookUnavailableException extends Throwable {
    public BookUnavailableException(String isbn) {
        super("Book with ISBN '" + isbn + "' is currently unavailable.");
    }
}

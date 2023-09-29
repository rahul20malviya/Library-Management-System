package com.eqMatrix;

import lombok.*;

//@AllArgsConstructor
//@Getter
//@Setter
@Data

public class Book {

    private String title;
    private String author;
    private String isbn;
    private String genre;
    private boolean available;
    private String price;

//public Book(){}

   public Book(String title, String author, String isbn, String genre,boolean available, String price) {
       this.title = title;
       this.author=author;
       this.isbn=isbn;
       this.genre=genre;
       this.available=available;
       this.price=price;
}

    public Book(String title, String author, String isbn, String genre, String price) {
        this.title = title;
        this.author=author;
        this.isbn=isbn;
        this.genre=genre;
        this.price=price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Title: ").append(title).append("\n");
        sb.append("Author: ").append(author).append("\n");
        sb.append("ISBN: ").append(isbn).append("\n");
        sb.append("Genre: ").append(genre).append("\n");
        sb.append("Available: ").append(available).append("\n");
        sb.append("Price: ").append(price).append("\n");
        return sb.toString();
    }

}


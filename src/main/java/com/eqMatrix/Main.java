package com.eqMatrix;
import java.util.Scanner;

public class Main {

    private static User findOrCreateUser(Library library, String userName) {
        for (User user : library.getUsers()) {
            if (user.getName().equalsIgnoreCase(userName)) {
                return user;
            }
        }
        User newUser = new User(userName);
        library.addUser(newUser);
        return newUser;
    }

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("--------Library Management System------------\n");
            System.out.println("1. Add Book");
            System.out.println("2. Display All Books");
            System.out.println("3. Search by Title");
            System.out.println("4. Search by Author");
            System.out.println("5. Search by Genre");
            System.out.println("6. Borrow Book");
            System.out.println("7. Return Book");
            System.out.println("8. Add User");
            System.out.println("9. View Library Card");
            System.out.println("0. Exit\n");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("\n----Add New Book----\n");
                    System.out.print("Enter Book Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Book Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    System.out.print("Enter Genre: ");
                    String genre = scanner.nextLine();
                    System.out.print("Enter Price: ");
                    String price = scanner.nextLine();

                    Book book = new Book(title,author,isbn,genre,price);
                    book.setAvailable(true);
                    //library.addBook(book);
                    //System.out.println("\nNew Book added successfully.\n");
                    try {
                        library.addBook(book);
                        System.out.println("\nBook added successfully.\n");
                    } catch (DuplicateIsbnException e) {
                        System.out.println("\nError: " + e.getMessage()+"\n");
                    }
                    break;

                case 2:
                    System.out.println("\n----Display All Books----\n");
                    library.displayAllBooks();
                    break;

                case 3:
                    System.out.println("\n----Search by Title----\n");
                    System.out.print("Enter Title to Search: ");
                    String searchTitle = scanner.nextLine();
                    library.searchByTitle(searchTitle);
                    break;

                case 4:
                    System.out.println("\n----Search by Author----\n");
                    System.out.print("Enter Author to Search: ");
                    String searchAuthor = scanner.nextLine();
                    library.searchByAuthor(searchAuthor);
                    break;

                case 5:
                    System.out.println("\n----Search by Genre----\n");
                    System.out.print("Enter Genre to Search: ");
                    String searchGenre = scanner.nextLine();
                    library.searchByGenre(searchGenre);
                    break;

                case 6:
                    System.out.println("\n----Borrow Book----\n");
                    System.out.print("Enter ISBN to Borrow: ");
                    String borrowIsbn = scanner.nextLine();
                    System.out.print("Enter User Name: ");
                    String userName = scanner.nextLine();

                    User user = findOrCreateUser(library, userName);
                    try {
                        library.borrowBook(borrowIsbn, user);
                        System.out.println("\nBook borrowed successfully.\n");
                    } catch (BookUnavailableException e) {
                        System.out.println("Error: " + e.getMessage()+"\n");
                    }
                    break;

                case 7:
                    System.out.println("\n----Return Book----\n");
                    System.out.print("Enter ISBN to Return: ");
                    String returnIsbn = scanner.nextLine();
                    System.out.print("Enter User Name: ");
                    String returnUserName = scanner.nextLine();

                    User returnUser = findOrCreateUser(library, returnUserName);
                    try {
                        library.returnBook(returnIsbn, returnUser);
                        System.out.println("\nBook returned successfully.\n");
                    } catch (BookNotFoundException e) {
                        System.out.println("Error: " + e.getMessage()+"\n");
                    }
                    break;

                case 8:
                    System.out.println("\n-----Add User----\n");
                    System.out.print("Enter User Name: ");
                    String newUser = scanner.nextLine();
                    User addedUser = new User(newUser);
                    library.addUser(addedUser);
                    System.out.println("\nUser added successfully.\n");
                    break;

                case 9:
                    System.out.println("\n----View Library Card----\n");
                    library.issuedToUser();
                    break;

                case 0:
                    System.out.println("\n----Exiting Library Management System.----\n");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("\n-----Invalid choice. Please try again.----\n");
            }
        }
    }

}






package org.isdb;

import org.isdb.model.Book;
import org.isdb.model.OracleConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
                Book book = new Book("Effective Java", "Joshua Bloch", 45.99, true);

                // Create an instance of the class containing addBooks method
                BookDatabase bookDatabase = new BookDatabase();

                // Add the book to the database
                bookDatabase.addBooks(book);
            }
        }

        class BookDatabase {
            // The method from your provided code
            private Connection getConnection() throws SQLException {
                return OracleConnection.getConnection();
            }

            // Create: Add new book
            public void addBooks(Book book) {
                String sql = "INSERT INTO BOOKS (title, author, price, available) VALUES (?, ?, ?, ?)";
                try (Connection connection = getConnection();
                     PreparedStatement statement = connection.prepareStatement(sql)) {

                    statement.setString(1, book.getTitle());  // Set the title of the book
                    statement.setString(2, book.getAuthor()); // Set the author of the book
                    statement.setDouble(3, book.getPrice());  // Set the price of the book
                    statement.setBoolean(4, book.isAvailable()); // Set availability of the book

                    statement.executeUpdate(); // Execute the insert statement
                    System.out.println("Book added to the database successfully!");
                } catch (SQLException e) {
                    System.err.println("Error: " + e.getMessage());
                }
            }
        }
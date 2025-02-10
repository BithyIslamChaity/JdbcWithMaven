package org.isdb.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookDAO {
    private Connection getConnection() throws SQLException {
        return OracleConnection.getConnection();
    }

    // Create: Add new book
    public void addBooks(Book book) {
        String sql = "INSERT INTO BOOKS (title, author, price, available) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Assuming book has these getter methods
            statement.setString(1, book.getTitle()); // Set the title of the book
            statement.setString(2, book.getAuthor()); // Set the author of the book
            statement.setDouble(3, book.getPrice());  // Set the price of the book
            statement.setBoolean(4, book.isAvailable()); // Set availability of the book

            statement.executeUpdate(); // Execute the insert statement
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }}


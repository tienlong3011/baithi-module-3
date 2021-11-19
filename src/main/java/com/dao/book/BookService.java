package com.dao.book;

import com.model.Book;
import com.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookService implements IBook {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/ex?useSSL=false";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "123456";

    private static final String INSERT_BOOK_SQL = "INSERT INTO book (id,nameBook,author,description,quantity) VALUES (?,?,?,?,?)";
    private static final String SELECT_BOOK_BY_ID = "select * from book where id =?";
    private static final String SELECT_ALL_BOOK = "select * from book";
    private static final String DELETE_BOOK_SQL = "delete from book where id = ?";
    private static final String UPDATE_BOOK_SQL = "update book set nameBook = ?,author = ?,description = ?,quantity = ? where id = ?";



    public static Connection getConnection() {
        Connection connection = null;
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            } catch (SQLException | ClassNotFoundException throwables) {
                throwables.printStackTrace();
            }
        }
        return connection;
    }

    @Override
    public void insert(Book book) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK_SQL)) {
            preparedStatement.setInt(1, book.getId());
            preparedStatement.setString(2, book.getNameBook());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getDescription());
            preparedStatement.setInt(5, book.getQuantity());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    @Override
    public Book selectById(int id) {
        Book book = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nameBook = resultSet.getString("nameBook");
                String author = resultSet.getString("author");
                String description = resultSet.getString("description");
                int quantity = resultSet.getInt("quantity");

                book = new Book(id, nameBook, author, description,quantity);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return book;
    }

    @Override
    public List<Book> selectAll() {
        List<Book> bookList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOK)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameBook = resultSet.getString("nameBook");
                String author = resultSet.getString("author");
                String description = resultSet.getString("description");
                int quantity = resultSet.getInt("quantity");;
                bookList.add(new Book(id, nameBook, author, description, quantity));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bookList;
    }

    @Override
    public boolean delete(int id) {
        boolean rowDelete = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK_SQL)) {
            preparedStatement.setInt(1, id);
            rowDelete = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowDelete;
    }


    @Override
    public boolean update(Book book) {
        boolean updateRow = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK_SQL)) {
            preparedStatement.setString(1, book.getNameBook());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getDescription());
            preparedStatement.setInt(4, book.getQuantity());
            preparedStatement.setInt(5, book.getId());
            updateRow = preparedStatement.executeUpdate() > 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return updateRow;
    }

    public boolean updateQuantityWhileBorrowed(Book book) throws SQLException{
        boolean rowUpdated;
        PreparedStatement statement = null;
        int newQuantity = book.getQuantity()-1;
        try {
            Connection connection = getConnection();
            statement = connection.prepareStatement("update book set quantity = ? where id = ?;");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        statement.setInt(1,newQuantity);
        statement.setInt(2,book.getId());
        rowUpdated = statement.executeUpdate()>0;
        return rowUpdated;
    }
}

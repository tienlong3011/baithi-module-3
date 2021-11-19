package com.dao.card;

import com.dao.book.BookService;
import com.dao.book.IBook;
import com.dao.student.IStudent;
import com.dao.student.StudentService;
import com.model.Book;
import com.model.Card;
import com.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CardService implements ICard{
    private static String jdbcURL = "jdbc:mysql://localhost:3306/ex?useSSL=false";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "123456";

    private static final String INSERT_CARD_SQL = "INSERT INTO card (idBook,idStudent,status,borrowDate,payDate) VALUES (?, ?, ?, ?,?)";
    private static final String SELECT_CARD_BY_ID = "select * from card where id =?";
    private static final String SELECT_ALL_CARD = "select * from card";
    private static final String DELETE_CARD_SQL = "delete from card where id = ?";
    private static final String UPDATE_CARD_SQL = "update book set idBook = ?,idStudent = ?,status = ?,borrowDate = ?,payDate = ? where id = ?";
    private final IBook bookService = new BookService();
    private final IStudent studentService = new StudentService();


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
    public void insert(Card card) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CARD_SQL)) {
            preparedStatement.setInt(1, card.getBook().getId());
            preparedStatement.setInt(2, card.getStudent().getId());
            preparedStatement.setInt(3, card.getStatus());
            preparedStatement.setDate(4, (Date) card.getBorrowerDate());
            preparedStatement.setDate(5, (Date) card.getPayDate());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Card selectById(int id) {
        Card card = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CARD_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idBook = resultSet.getInt("idBook");
                int idStudent = resultSet.getInt("idStudent");
                int status = resultSet.getInt("status");
                Date borrowDate = resultSet.getDate("borrowDate");
                Date payDate = resultSet.getDate("payDate");
                Book book = bookService.selectById(idBook);
                Student student = studentService.selectById(idStudent);

                card = new Card(id, book, student, status,borrowDate,payDate);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return card;

    }

    @Override
    public List<Card> selectAll() {
        List<Card> cardList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CARD)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idBook = resultSet.getInt("idBook");
                int idStudent = resultSet.getInt("idStudent");
                int status = resultSet.getInt("status");
                Date borrowDate = resultSet.getDate("borrowDate");
                Date payDate = resultSet.getDate("payDate");
                Book book = bookService.selectById(idBook);
                Student student = studentService.selectById(idStudent);
                cardList.add(new Card(id, book,student, status, borrowDate,payDate));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cardList;

    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Card card) throws SQLException {
        return false;
    }
}

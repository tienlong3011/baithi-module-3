package com.dao.student;

import com.dao.IDao;
import com.model.Book;
import com.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements IStudent {

    private static String jdbcURL = "jdbc:mysql://localhost:3306/ex?useSSL=false";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "123456";

    private static final String INSERT_STUDENT_SQL = "INSERT INTO student (nameStudent,className) VALUES (?, ?)";
    private static final String SELECT_STUDENT_BY_ID = "select * from student where id =?";
    private static final String SELECT_ALL_STUDENT = "select * from student";
    private static final String DELETE_STUDENT_SQL = "delete from student where id = ?";
    private static final String UPDATE_STUDENT_SQL = "update student set nameStudent = ?,className = ? where id = ?";



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
    public void insert(Student student) throws SQLException {

    }

    @Override
    public Student selectById(int id) {
        Student student = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nameStudent = resultSet.getString("nameStudent");
                String className = resultSet.getString("className");
                student = new Student(id, nameStudent, className);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> selectAll() {
        List<Student> studentList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENT)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nameStudent = resultSet.getString("nameStudent");
                String className = resultSet.getString("className");
                studentList.add(new Student(id, nameStudent, className));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return studentList;

    }

    @Override
    public boolean delete(int id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Student student) throws SQLException {
        return false;
    }
}

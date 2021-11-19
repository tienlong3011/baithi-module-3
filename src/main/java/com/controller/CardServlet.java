package com.controller;



import com.dao.book.BookService;
import com.dao.book.IBook;
import com.dao.card.CardService;
import com.dao.card.ICard;
import com.dao.student.IStudent;
import com.dao.student.StudentService;
import com.model.Book;
import com.model.Card;
import com.model.Student;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;



@WebServlet(name = "CardServlet", urlPatterns = "/card")
public class CardServlet extends HttpServlet {
    private final BookService bookService = new BookService();
    private final IStudent studentService = new StudentService();
    private final ICard cardService = new CardService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "borrow":
                showBorrow(request,response);
                break;
            default:
                listCards(request, response);
                break;
        }
    }

    private void listCards(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("card/list.jsp");

        request.setAttribute("cardList",cardService.selectAll());
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }


    private void showBorrow(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Book book = bookService.selectById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("card/borrow.jsp");
        List <Student> studentList = studentService.selectAll();
        request.setAttribute("book",book);
        request.setAttribute("studentList",studentList);
        try {
            dispatcher.forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "borrow":
                borrow(request,response);
                break;
            default:
                listCards(request, response);
                break;

        }
    }

    private void borrow(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher("card/borrow.jsp");
        int bookId = Integer.parseInt(request.getParameter("idBook"));
        Book book = null;
        Student student = null;
        try {
            book = bookService.selectById(bookId);
            bookService.updateQuantityWhileBorrowed(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int studentId = Integer.parseInt(request.getParameter("student"));
        student = studentService.selectById(studentId);
       java.sql.Date borrowDate = java.sql.Date.valueOf(request.getParameter("borrow"));
        java.sql.Date payDate = java.sql.Date.valueOf(request.getParameter("pay"));
        Card card = new Card(book,student,1,borrowDate,payDate);
        try {
            cardService.insert(card);
            request.setAttribute("cardList",cardService.selectAll());
            dispatcher.forward(request,response);
        } catch (SQLException | ServletException | IOException e) {
            e.printStackTrace();
        }
        }


}




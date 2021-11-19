package com.controller;

import com.dao.book.BookService;
import com.dao.book.IBook;
import com.dao.student.IStudent;
import com.model.Book;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;



    @WebServlet(name = "BookServlet", urlPatterns = "/books")
    public class BookServlet extends HttpServlet {
        private final IBook bookService = new BookService();

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) {
            String action = request.getParameter("action");
            if (action == null) {
                action = "";
            }
            switch (action) {
                case "create":
                    showCreateForm(request, response);
                    break;
                default:
                    listBook(request, response);
                    break;
            }
        }




        private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("book/create.jsp");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        }

        private void listBook(HttpServletRequest request, HttpServletResponse response) {
            List<Book> bookList = bookService.selectAll();
            RequestDispatcher dispatcher = request.getRequestDispatcher("book/list.jsp");
            request.setAttribute("bookList", bookList);
            try {
                dispatcher.forward(request, response);
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
                case "create":
                    createBook(request, response);
                    break;

            }
        }



        private void createBook(HttpServletRequest request, HttpServletResponse response) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nameBook = request.getParameter("nameBook");
            String author = request.getParameter("author");
            String description = request.getParameter("description");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            Book book = new Book(id,nameBook, author, description, quantity);
            try {
                bookService.insert(book);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("book/create.jsp");
            request.setAttribute("message", "Successful new creation!");
            try {
                dispatcher.forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }


        }
    }



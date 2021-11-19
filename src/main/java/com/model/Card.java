package com.model;

import java.time.LocalDate;
import java.util.Date;

public class Card {
    private int id;
    private Book book;
    private Student student;
    private int status;
    private Date borrowerDate;
    private Date payDate;

    public Card() {
    }

    public Card(int id, Book book, Student student, int status, Date borrowerDate, Date payDate) {
        this.id = id;
        this.book = book;
        this.student = student;
        this.status = status;
        this.borrowerDate = borrowerDate;
        this.payDate = payDate;
    }

    public Card(Book book, Student student, int status, Date borrowerDate, Date payDate) {
        this.book = book;
        this.student = student;
        this.status = status;
        this.borrowerDate = borrowerDate;
        this.payDate = payDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getBorrowerDate() {
        return borrowerDate;
    }

    public void setBorrowerDate(Date borrowerDate) {
        this.borrowerDate = borrowerDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
}

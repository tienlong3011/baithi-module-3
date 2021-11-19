<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 19/11/2021
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

</head>
<body>

<div align="center">
    <h1 style="color: white">Cards Management</h1>
    <h3 style="color: white"><a href="/books" >Library</a></h3>
    <table border="1" class="table table-warning table-striped table-hover">
        <tr>
            <th><a href="/books?action=sort">Card Code</a></th>
            <th>Book</th>
            <th>Author</th>
            <th>Student</th>
            <th>Class</th>
            <th>BorrowDate</th>
            <th>ReturnDate</th>
            <th>Return</th>
        </tr>
        <c:forEach items="${cardList}" var="card">
            <tr>
                <td>${card.id}</td>
                <td><a href="/cards?action=view&id=${card.id}">${card.getBook().name}</a></td>
                <td>${card.getBook().author}</td>
                <td>${card.getStudent().name}</td>
                <td>${card.getStudent().className}</td>
                <td>${card.borrowDate}</td>
                <td>${card.returnDate}</td>
                <td><a href="/cards?action=return&id=${card.id}">Return</a></td>
            </tr>

        </c:forEach>
    </table>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
        integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
        integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
        crossorigin="anonymous"></script>

</html>

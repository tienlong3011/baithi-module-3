<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 19/11/2021
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<html>
<head>
    <title>List Book</title>
</head>
<style>
    div {
        margin-left: 400px;
        width: 700px;
    }

</style>
<body>
<center>
    <h1><a href="/books" style="color: #dc3545; text-decoration: none">BOOK MANAGER</a></h1>
    <h2><a href="/books?action=create" style=" color: black; text-decoration: none">Add new Book</a></h2>
</center>
<div align="center">
    <table border="1" cellpadding="5" class="table table-info table-hover table-striped">
        <tr>
            <td colspan="6" style="text-align: center"><h4>List of Book</h4></td>
        </tr>
        <tr>
            <th>ID</th>
            <th>Name Book</th>
            <th>Author</th>
            <th>Description</th>
            <th>Quantity</th>
            <th>Actions</th>

        </tr>
        <c:forEach items='${requestScope["bookList"]}' var="b">
            <tr>
                <td><c:out value="${b.id}"/></td>
                <td><c:out value="${b.nameBook}"/></td>
                <td><c:out value="${b.author}"/></td>
                <td><c:out value="${b.description}"/></td>
                <td><c:out value="${b.quantity}"/></td>
                <td>
                    <a href="/card?action=borrow&id=${b.id}">Mượn</a>
<%--                    <a href="/books?action=delete&id=${b.id}">Delete</a>--%>
                </td>
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

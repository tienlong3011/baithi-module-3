<%--
  Created by IntelliJ IDEA.
  User: ACER
  Date: 19/11/2021
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<html>
<head>
    <title>Mượn Sách</title>
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
</center>
<div align="center">
    <form method="post" action="/card?action=borrow">
    <table border="1" cellpadding="5" class="table table-info table-hover table-striped">
        <tr>
            <td colspan="2" style="text-align: center"><h4>Borrow Book</h4></td>
        </tr>
        <tr>
            <th>Sách mượn</th>
            <td><input type="text" name="nameBook" value="${book.nameBook}" disabled></td>
            <input type="text" name="idBook" value="${book.id}" hidden>
        </tr>
        <tr>
            <th>Tên học sinh:</th>
            <td>
                <select name="student">
                    <c:forEach items="${studentList}" var="s">
                        <option value="${s.id}">${s.nameStudent}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <th>Ngày mượn:</th>
            <td><input type="date" name="borrow"></td>
        </tr>
        <tr>
            <th>Ngày trả:</th>
            <td><input type="date" name="pay"></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Save" class="btn btn-outline-info"></td>
        </tr>
    </table>
    </form>
    <p class="test">
        <c:if test="${message!= null}">
            <span>${message}</span>
        </c:if>
    </p>
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

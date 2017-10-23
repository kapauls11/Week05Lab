<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Shopping List</h1>
        <form action="ShoppingList?action=register" method="post">
            Username: <input type="text" name="username" value="${username}">
            <input type="submit" value="Register Name">
        </form>
        <br>
        ${registerMessage}
    </body>
</html>

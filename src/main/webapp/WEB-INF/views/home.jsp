<%--
  Created by IntelliJ IDEA.
  User: jkonstantynowicz
  Date: 2018-11-17
  Time: 09:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Strona startowa</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="resources/css.css">
</head>
<body>
<div class="topnav">
    <a class="active" href="#home">Home</a>
    <a href="#about">About</a>
    <a href="#contact">Contact</a>
    <div class="login-container">
        <form action="/action_page.php">
            <input type="text" placeholder="Username" name="username">
            <input type="password" placeholder="Password" name="psw">
            <button type="submit">Login</button>
        </form>
    </div>
</div>
<form method="POST" action="uploadFile" enctype="multipart/form-data">
    <table>
    <tr>
    <td>Select a file to upload</td>
    <td><input type="file" name="file"  accept=".csv"/></td>
    </tr>
    <tr>
    <td><input type="submit" value="Submit" /></td>
    </tr>
    </table>
    </form>
</body>
</html>

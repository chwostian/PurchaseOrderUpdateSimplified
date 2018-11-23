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
</head>
<body>
<form method="POST" action="uploadFile" enctype="multipart/form-data">
    <table>
    <tr>
    <td>Select a file to upload</td>
    <td><input type="file" name="file" /></td>
    </tr>
    <tr>
    <td><input type="submit" value="Submit" /></td>
    </tr>
    </table>
    </form>
</body>
</html>

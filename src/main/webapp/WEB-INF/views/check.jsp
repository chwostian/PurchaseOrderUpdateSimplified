<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jkonstantynowicz
  Date: 2018-11-17
  Time: 10:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>check</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="resources/css2.css">
</head>
<body>
<h2>Submitted File</h2>
<table>
    <thead>
    <tr>
        <th>SUPPLIER</th>
        <th>CODE</th>
        <th>PO</th>
        <th>LINE</th>
        <th>SKU</th>
        <th>CONFIRMED DELIVERY DATE</th>
        <th>REMAINING QUANTITY</th>
    </tr>
    </thead>
    <c:forEach var="item" items="${fileContent}" >
       <tbody>
        <tr>
            <td>${item.SUPPLIER}</td>
            <td>${item.CODE}</td>
            <td>${item.PO}</td>
            <td>${item.LINE}</td>
            <td>${item.SKU}</td>
            <td>${item.CONFIRMED_DELIVERY_DATE}</td>
            <td>${item.REMAINING_QUANTITY}</td>


        </tr>
    </c:forEach>
       </tbody>
</table>
</body>
</html>

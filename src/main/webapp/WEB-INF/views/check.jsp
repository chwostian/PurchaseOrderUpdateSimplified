<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

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
    <link rel="stylesheet" type="text/css" href="resources/css2.css"/>
    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.5.0/css/all.css' integrity='sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU' crossorigin='anonymous'>
</head>
<body>
<div><h2>Submitted File</h2></div>
<table id="comparison">
    <thead>
    <tr >
        <th class="sticky" colspan="5">Common data</th>
        <th class="sticky" colspan="4">Fibaro data</th>
        <th class="sticky" colspan="2">Supplier data</th>
        <th class="sticky" rowspan="2">Comment</th>
    </tr>
    <tr>
        <th class="sticky2">Supplier</th>
        <th class="sticky2">Code</th>
        <th class="sticky2">Purchase order</th>
        <th class="sticky2">Line</th>
        <th class="sticky2">SKU</th>
        <th class="sticky2">Requested<br>delivery date</th>
        <th class="sticky2">Confirmed<br> delivery date</th>
        <th class="sticky2">Original<br>quantity</th>
        <th class="sticky2">Remaining<br>quantity</th>
        <th class="sticky2">Confirmed<br>delivery date</th>
        <th class="sticky2">Remaining<br>quantity</th>
    </tr>
    </thead>
    <c:forEach var="item" items="${fullContent}" >
       <tbody>
        <tr>
            <td>${item.nazwa_pelna}</td>
            <td>${item.numer_kontrahenta}</td>
            <td>${item.numer_zamowienia}</td>
            <td>${item.numer_pozycji}</td>
            <td>${item.indeks}</td>
            <td contenteditable="true"><input type="date" value="${item.kl_termin}"/></td>
            <td contenteditable="true"><input type="date" value="${item.pr_termin}"/></td>
            <td>${item.ilosc_zlecona}</td>
            <td>${item.ilosc_do_przyjecia}</td>
            <td><input type="date" value="${item.termin_dostawcy}"/></td>
            <td>${item.ilosc_do_przyjecia_wg_dostawcy}</td>
            <td id="comment" contenteditable="true">${item.uwagi}</td>
            <fmt:formatDate type="date" value="${item.kl_termin}" pattern="yyyy-MM-dd" var="Date1" />
            <fmt:formatDate type="date" value="${item.termin_dostawcy}" pattern="yyyy-MM-dd" var="Date2" />
            <c:choose>
                <c:when test="${Date1 eq Date2}">
                    <td class="fas fa-thumbs-up">${Date1 eq Date2} oraz ${Date1} oraz ${Date2}</td>
                </c:when>
                <c:otherwise><td class="fas fa-thumbs-down">${Date1 eq Date2}</td></c:otherwise>
            </c:choose>
        </tr>
    </c:forEach>
       </tbody>
</table>


</body>
</html>

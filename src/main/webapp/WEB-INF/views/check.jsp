<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.5.0/css/all.css'
          integrity='sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU' crossorigin='anonymous'>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>
<body>
<div><h2>Submitted File</h2></div>
<table id="comparison">
    <thead>
    <tr>
        <th class="sticky" colspan="5">Common data</th>
        <th class="sticky" colspan="4">Fibaro data</th>
        <th class="sticky" colspan="2">Supplier data</th>
        <th class="sticky" rowspan="2">Comment</th>
        <th class="sticky" rowspan="2">Dates match?</th>
        <th class="sticky" rowspan="2">Remaining<br>quantities<br></th>
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
    <c:forEach var="item" items="${fullContent}">

    <fmt:parseDate type="date" value="${item.kl_termin}" pattern="yyyy-MM-dd" var="parsed_kl_termin"/>
    <fmt:parseDate type="date" value="${item.termin_dostawcy}" pattern="yyyy-MM-dd" var="parsed_termin_dostawcy"/>
    <fmt:parseDate type="date" value="${item.pr_termin}" pattern="yyyy-MM-dd" var="parsed_pr_termin"/>
    <fmt:formatNumber type="number" value="${item.ilosc_zlecona}" groupingUsed="true" var="n_ilosc_zlecona"/>
    <fmt:formatNumber type="number" value="${item.ilosc_do_przyjecia}" groupingUsed="true" var="n_ilosc_do_przyjecia"/>
    <fmt:formatNumber type="number" value="${item.ilosc_do_przyjecia_wg_dostawcy}" groupingUsed="true"
                      var="n_ilosc_do_przyjecia_wg_dostawcy"/>

    <%System.out.println(request.getAttribute("parsed_pr_termin") + "/" + request.getAttribute("item.pr_termin"));%>

    <tbody>
        <tr class = "values"
            data-nazwa_pelna="${item.nazwa_pelna}"
            data-numer_kontrahenta="${item.numer_kontrahenta}"
            data-numer_zamowienia="${item.numer_zamowienia}"
            data-numer_pozycji="${item.numer_pozycji}"
            data-indeks="${item.indeks}"
            data-ilosc_zlecona="${n_ilosc_zlecona}"
            data-n_ilosc_do_przyjecia="${n_ilosc_do_przyjecia}"
            data-kl_termin= "${item.kl_termin}"
            data-pr_termin="${item.pr_termin}"
            data-termin_dostawcy="${item.termin_dostawcy}"
            data-n_ilosc_do_przyjecia_wg_dostawcy="${n_ilosc_do_przyjecia_wg_dostawcy}"
            data-uwagi="${item.uwagi}"
            data-hidden="true"
        >
            <td data-name="nazwa_pelna">${item.nazwa_pelna}</td>
            <td data-name="numer_kontrahenta">${item.numer_kontrahenta}</td>
            <td data-name="numer_zamowienia">${item.numer_zamowienia}</td>
            <td data-name="numer_pozycji">${item.numer_pozycji}</td>
            <td data-name="indeks">${item.indeks}</td>
            <td><input data-name="kl_termin" type="date" value="${item.kl_termin}" contenteditable="true"/></td>
            <td><input data-name="pr_termin" type="date" value="${item.pr_termin}" contenteditable="true"/></td>
            <td data-name="n_ilosc_zlecona">${n_ilosc_zlecona}</td>
            <td data-name="n_ilosc_do_przyjecia">${n_ilosc_do_przyjecia}</td>
            <td data-name="termin_dostawcy">${item.termin_dostawcy}</td>
            <td data-name="n_ilosc_do_przyjecia_wg_dostawcy">${n_ilosc_do_przyjecia_wg_dostawcy}</td>
            <td data-name="uwagi" id="comment"><textarea data-name="uwagi" type="text" contenteditable="true" cols="30" >${item.uwagi}</textarea></td>


            <c:choose>
                <c:when test="${((parsed_kl_termin eq parsed_termin_dostawcy) && (not empty parsed_pr_termin) && (not empty parsed_termin_dostawcy)) || (parsed_termin_dostawcy eq parsed_pr_termin)}">
                    <td class="center"><i class="glyphicon glyphicon-ok"/></td>
                </c:when>
                <c:otherwise>
                    <td class="center"><i class="glyphicon glyphicon-remove"/></td>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${item.ilosc_zlecona eq item.ilosc_do_przyjecia_wg_dostawcy}">
                    <td class="center"><i class="glyphicon glyphicon-ok"/></td>
                </c:when>
                <c:otherwise>
                    <td class="center"><i class="glyphicon glyphicon-remove"/></td>
                </c:otherwise>
            </c:choose>

        </tr>
        </c:forEach>
    </tbody>
</table>


<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/app.js"></script>

</body>
</html>

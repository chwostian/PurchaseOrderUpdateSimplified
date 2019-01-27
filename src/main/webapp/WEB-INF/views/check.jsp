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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

</head>
<body>
<div><h2>Submitted File ${size} ${fileName}</h2></div>
<table id="comparison">
    <thead>
    <tr>

        <th id="update" class="sticky0" colspan="3">Update purchase orders <i class="fa fa-database"/></th>
        <th class="sticky" colspan="2"><a href="/PurchaseOrderUpdaterSimplified/uploadFile" method="get">Upload file <i class="fa fa-download"></i></a></th>
        <th id="export_to_excel" class="sticky0" >Export to Ms Excel <i class="fa fa-file-excel-o"/></th>
        <th class="sticky0" >Export to pdf <i class="fa fa-file-pdf-o"/></th>
        <th class="sticky0" colspan="2">Send mail <i class="fa fa-send-o"/></th>

    </tr>
    <tr>
        <th class="sticky1" colspan="5">Common data</th>
        <th class="sticky1" colspan="4">Fibaro data</th>
        <th class="sticky1" colspan="2">Supplier data</th>
        <th class="sticky3" rowspan="2">Comment</th>
        <th class="sticky3" rowspan="2">Dates<br>match?</th>
        <th class="sticky3" rowspan="2">Remaining<br>quantities<br></th>
        <th class = "sticky3" rowspan="2">Options</th>
    </tr>
    <tr>
        <th class="sticky2">Code</th>
        <th class="sticky2">Purchase order</th>
        <th class="sticky2">Line</th>
        <th class="sticky2">SKU</th>
        <th class="sticky2">Description</th>
        <th class="sticky2">Requested<br>delivery date</th>
        <th class="sticky2">Confirmed<br>delivery date</th>
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
            data-indeks_czesci="${item.indeks_czesci}"
            data-nazwa_pelna="${item.nazwa_czesci}"
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
            <td data-name="numer_kontrahenta">${item.numer_kontrahenta}</td>
            <td data-name="numer_zamowienia">${item.numer_zamowienia}</td>
            <td data-name="numer_pozycji">${item.numer_pozycji}</td>
            <td data-name="indeks">${item.indeks}</td>
            <td data-name="nazwa_czesci" class="description">${item.nazwa_czesci}</td>
            <td><input data-name="kl_termin" type="date" value="${item.kl_termin}" contenteditable="true"/></td>
            <td><input data-name="pr_termin" type="date" value="${item.pr_termin}" contenteditable="true"/></td>
            <td><input data-name="n_ilosc_zlecona" type="text" value="${n_ilosc_zlecona}" contenteditable="true"/></td>
            <td data-name="n_ilosc_do_przyjecia">${n_ilosc_do_przyjecia}</td>
            <td data-name="termin_dostawcy">${item.termin_dostawcy}</td>
            <td data-name="n_ilosc_do_przyjecia_wg_dostawcy">${n_ilosc_do_przyjecia_wg_dostawcy}</td>
            <td data-name="uwagi" class="comment"><textarea data-name="uwagi" type="text" contenteditable="true" cols="30" >${item.uwagi}</textarea></td>


            <c:choose>
                <c:when test="${item.daty_sie_zgadzaja==true}">
                    <td class="center"><i class="glyphicon glyphicon-ok"/></td>
                </c:when>
                <c:otherwise>
                    <td class="center"><i class="glyphicon glyphicon-remove"/></td>
                </c:otherwise>
            </c:choose>

            <c:choose>
                <c:when test="${item.ilosci_sie_zgadzaja==true}">
                    <td class="center"><i class="glyphicon glyphicon-ok"/></td>
                </c:when>
                <c:otherwise>
                    <td class="center"><i class="glyphicon glyphicon-remove"/></td>
                </c:otherwise>
            </c:choose>
            <td data-button="true"><button class="remove">Remove row</button></td>
        </tr>

        </c:forEach>
    </tbody>

</table><br>


<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/check.js"></script>

</body>
</html>

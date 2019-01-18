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
    <link rel="stylesheet" type="text/css" href="resources/css.css"/>
    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.5.0/css/all.css'
          integrity='sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU' crossorigin='anonymous'>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<nav>
    <div>PurchaseOrdersUpdaterSimplified</div>
</nav>

<form class = "container hide_or_show" data-hidden ="${hide_or_show}" method="POST" action="createSessionAttributes" style="max-width:500px;margin:auto">
    <h2>Logowanie</h2>
    <h6 class="sql-exception">${sqlException}</h6>
    <div class="input-container">
        <i class="fa fa-user icon"></i>
        <input class="input-field" type="text" placeholder="Użytkownik Impuls" name="user">
    </div>

    <div class="input-container">
        <i class="fa fa-key icon"></i>
        <input class="input-field" type="password" placeholder="Hasło do Impuls" name="password">
    </div>

    <button type="submit" class="btn">Wyślij</button>
</form>



<form class="" id="uploader" method="POST" data-hidden="${!hide_or_show}" action="uploadFile" enctype="multipart/form-data">


    <div id="upload">
        <br><span> Choose a file to upload for analysis </span>
            <input id="fileToUpload" type="file" name="file"  accept=".csv, .xlsx"/>
            <div><i class="fa fa-cloud-upload"></i></div>

    </div>
    <div class="submit"><input type="submit"/>Upload</div>

</form>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="resources/js/home.js"></script>
</body>
</html>

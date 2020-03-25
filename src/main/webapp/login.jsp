<%--
  Created by IntelliJ IDEA.
  User: apple97
  Date: 25/03/20
  Time: 11:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <title>Bienvenido a twitter</title>
</head>
<body style="background-color: rgb(21, 32, 43);">
<div class="jumbotron text-center">
    <img src="https://www.pinclipart.com/picdir/big/388-3884568_twitter-black-circle-twitter-round-logo-png-clipart.png" class="rounded" alt="Twitter" width="304" height="236">
</div>
<div class="container" style="color:white;">
    <form action="LoginAction">
        <div class="form-group">
            <label for="usr">User:</label>
            <input type="text" class="form-control" id="usr" name="usuarioBean.id_usuario">
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="pwd" name="usuarioBean.pass">
        </div>
        <button type="submit" class="btn btn-primary">Log in</button>
    </form>
</div>


</body>
</html>

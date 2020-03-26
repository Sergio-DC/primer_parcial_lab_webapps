<%--
  Created by IntelliJ IDEA.
  User: apple97
  Date: 25/03/20
  Time: 19:36
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
    <title>Feed</title>
</head>
<body style="background-color: rgb(21, 32, 43);">
<div class="jumbotron text-center">
    <img src="https://www.pinclipart.com/picdir/big/388-3884568_twitter-black-circle-twitter-round-logo-png-clipart.png" class="rounded" alt="Twitter" width="304" height="236">
</div>
<div class="container" style="color:white;">
    <aside>
        <h4>Epcot Center</h4>
        <p>Epcot is a theme park at Walt Disney World Resort featuring exciting attractions, international pavilions, award-winning fireworks and seasonal special events.</p>
    </aside>
    <div class="form-group">
        <textarea class="form-control" id="newComment" rows="3" placeholder="Escribe tu comentario aqui"></textarea>
        <br>
        <button type="submit" class="btn btn-primary float-right">Comment</button>
    </div>

</div>
<br>
<div class="container"  id="comentarios">
    <br>
    <div class="card">
        <div class="card-body">
            <div id="container" style="white-space:nowrap" class="card-title">

                <div id="image" style="display:inline;">
                    <img src="https://www.pinclipart.com/picdir/big/388-3884568_twitter-black-circle-twitter-round-logo-png-clipart.png" class="rounded" alt="Twitter" width="20" height="20"/>
                </div>

                <div id="texts" style="display:inline; white-space:nowrap;">
                    Alex
                </div>

            </div>​
            <p class="card-text">Prueba.</p>
            <a href="#" class="card-link">responder</a>
            <a href="#" class="card-link">editar</a>
            <a href="#" class="card-link">borrar</a>
        </div>
    </div>
</body>
</html>

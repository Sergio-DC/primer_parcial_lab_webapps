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
<div class="modal" id="answerModal" >
    <div class="modal-dialog">
        <div class="modal-content">
            <input type="hidden" id="ansId" name="ansId" value="0">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Responder a comentario</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <div class="form-group">
                    <textarea class="form-control" id="answerComment" rows="3" placeholder="Escribe tu comentario aqui" id="answerComment"></textarea>
                </div>
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary" data-dismiss="modal" id="answerCommentB">Comment</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>

<div class="modal" id="editModal">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <input type="hidden" id="editId" name="editId" value="0">
                <h4 class="modal-title">Modificar comentario</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>

            <!-- Modal body -->
            <div class="modal-body">
                <div class="form-group">
                    <textarea class="form-control" id="editComment" rows="3" placeholder="Escribe tu comentario aqui"></textarea>
                </div>
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary" data-dismiss="modal" id="editCommentB">Edit</button>
                <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>
<div class="jumbotron text-center">
    <img src="https://www.pinclipart.com/picdir/big/388-3884568_twitter-black-circle-twitter-round-logo-png-clipart.png" class="rounded" alt="Twitter" width="304" height="236">
</div>
<div class="d-none d-xl-block float-right" style="color:white; margin-right: 25px; font-size:2vw;">
    <a href="feed.jsp">Home</a>
    <br>
    <a href="#">News</a>
    <br>
    <a href="#">Contact</a>
    <br>
    <a href="login.jsp">log out</a>
</div>
<div class="container" style="color:white;">
    <div class="form-group">
        <textarea class="form-control" id="newComment" rows="3" placeholder="Escribe tu comentario aqui"></textarea>
        <br>
        <button type="submit" class="btn btn-primary float-right" id="newCommentB">Comment</button>
    </div>

</div>
<br>
<div class="container"  id="comentarios">
    <br>
    <div class="card d-none">
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
            <a href="#" data-toggle="modal" data-target="#answerModal" class="card-link">responder</a>
            <a href="#" data-toggle="modal" data-target="#editModal" class="card-link">editar</a>
            <a href="#" class="card-link">borrar</a>
        </div>
    </div>
</div>
</body>
<script>
    $(document).ready(function(){
        getPost()
    });

    $("#newCommentB").click(function(){
        newPost(-1);
    });

    $("#answerCommentB").click(function(){
        newPost($("#ansId").val());
    });

    $("#editCommentB").click(function(){
        editPost();
    });

    function answer(ids) {
        $("#ansId").val(ids);
        //newPost(id);
    }

    function edit(ids) {
        $("#editId").val(ids);
        $("#editComment").val($("#editText"+ids+"").val());
    }

    function editPost() {
        var idC = $("#editId").val();
        var comentario = $("#editComment").val();
        var json = {"comentario":comentario,"idC":idC};
        $.ajax({
            url: "modificar_contenido",
            type: 'POST',
            dataType: 'json',
            data: json,
            success:function(response){
                alert("Comentario Modificado");
                $("#comentarios").empty();
                getPost();

            },
            error:function(jqXhr, textStatus, errorThrown){
                alert("Error al comentar");
            }
        });
    }

    function borrar(ids) {
        console.log(ids);
        var json = {"idC":ids};
        $.ajax({
            url: "eliminar_contenido",
            type: 'POST',
            dataType: 'json',
            data: json,
            success:function(response){
                alert("Comentario eliminado");
                $("#comentarios").empty();
                getPost();

            },
            error:function(jqXhr, textStatus, errorThrown){
                alert("Error al eliminar");
            }
        });
    }



    function newPost(respuesta) {
        if(respuesta == -1) var comentario = $('#newComment').val();
        else var comentario = $('#answerComment').val()
        var resA = respuesta;
        var json = {"comentario":comentario,"resA":resA};
        $.ajax({
            url: "crear_nuevo_contenido",
            type: 'POST',
            dataType: 'json',
            data: json,
            success:function(response){
                alert("Comentario agregado");
                $("#newComment").val("");
                $("#answerComment").val("");
                $("#comentarios").empty();
                getPost();

            },
            error:function(jqXhr, textStatus, errorThrown){
                alert("Error al comentar");
            }
        });
    }
    function getPost(){


        var json;
        $.ajax({
            url: "cargar_contenido",
            type: 'POST',
            dataType: 'json',
            data: json,
            success:function(response){
                console.log("Response: ");
                console.log(response);
                response.lista_comentario_usuario.forEach(function(index) {
                    var fecha = "";
                    fecha = "" +index.fecha_publicacion.dayOfMonth+ "-" +index.fecha_publicacion.monthValue+ "-" +index.fecha_publicacion.year;
                    if(index.eliminado == 1) $("#com"+index.id_comentario+"").hide();
                    if(index.id_respuesta_a == 0) {
                    $("#comentarios").append(" <br>\n" +
                        "    <div class=\"card\" id=\"com"+index.id_comentario+"\">\n" +
                        "        <div class=\"card-body\">\n" +
                        "            <div id=\"container\" style=\"white-space:nowrap\" class=\"card-title\">\n" +
                        "\n" +
                        "                <div id=\"image\" style=\"display:inline;\">\n" +
                        "                    <img src=\"https://www.pinclipart.com/picdir/big/388-3884568_twitter-black-circle-twitter-round-logo-png-clipart.png\" class=\"rounded\" alt=\"Twitter\" width=\"20\" height=\"20\"/>\n" +
                        "                </div>\n" +
                        "\n" +
                        "                <div id=\"texts\" style=\"display:inline; white-space:nowrap;\">\n" +
                        "                    "+index.nombre+"\n" +
                        "                </div>\n" +
                        "\n" +
                        "            </div>​\n" +
                        "            <input type=\"hidden\" id=\"editText"+index.id_comentario+"\" name=\"editText\" value=\""+index.contenido+"\">" +
                        "            <p class=\"card-text\">"+fecha+"<br>"+index.contenido+"</p>\n" +
                        "            <a href=\"#\" data-toggle=\"modal\" data-target=\"#answerModal\" class=\"card-link\" id=\"res"+index.id_comentario+"\" onclick=\"answer("+index.id_comentario+")\">responder</a>\n" +
                        "            <a href=\"#\" data-toggle=\"modal\" data-target=\"#editModal\" class=\"card-link\" id=\"edit"+index.id_comentario+"\" onclick=\"edit("+index.id_comentario+")\">editar</a>\n" +
                        "            <a href=\"#\" class=\"card-link\" onclick=\"borrar("+index.id_comentario+")\">borrar</a>\n" +
                        "        </div>\n" +
                        "    </div>");}
                });
                response.lista_comentario_usuario.forEach(function(index) {
                    var fecha = "";
                    fecha = "" +index.fecha_publicacion.dayOfMonth+ "-" +index.fecha_publicacion.monthValue+ "-" +index.fecha_publicacion.year;
                    if(index.eliminado == 1) $("#com"+index.id_comentario+"").hide();
                        if(index.id_respuesta_a != 0) {
                            $("#com"+index.id_respuesta_a+"").after(" <br>\n" +
                                "    <div class=\"card col-8\" id=\"com"+index.id_comentario+"\">\n" +
                                "        <div class=\"card-body\">\n" +
                                "            <div id=\"container\" style=\"white-space:nowrap\" class=\"card-title\">\n" +
                                "\n" +
                                "                <div id=\"image\" style=\"display:inline;\">\n" +
                                "                    <img src=\"https://www.pinclipart.com/picdir/big/388-3884568_twitter-black-circle-twitter-round-logo-png-clipart.png\" class=\"rounded\" alt=\"Twitter\" width=\"20\" height=\"20\"/>\n" +
                                "                </div>\n" +
                                "\n" +
                                "                <div id=\"texts\" style=\"display:inline; white-space:nowrap;\">\n" +
                                "                    "+index.nombre+"\n" +
                                "                </div>\n" +
                                "\n" +
                                "            </div>​\n" +
                                "            <input type=\"hidden\" id=\"editText"+index.id_comentario+"\" name=\"editText\" value=\""+index.contenido+"\">" +
                                "            <p class=\"card-text\">"+fecha+"<br>"+index.contenido+"</p>\n" +
                                "            <a href=\"#\" data-toggle=\"modal\" data-target=\"#answerModal\" class=\"card-link\" id=\"res"+index.id_comentario+"\" onclick=\"answer("+index.id_comentario+")\">responder</a>\n" +
                                "            <a href=\"#\" data-toggle=\"modal\" data-target=\"#editModal\" class=\"card-link\" id=\"edit"+index.id_comentario+"\" onclick=\"edit("+index.id_comentario+")\">editar</a>\n" +
                                "            <a href=\"#\" class=\"card-link\" onclick=\"borrar("+index.id_comentario+")\">borrar</a>\n" +
                                "        </div>\n" +
                                "    </div>");

                        }
                });

            },
            error:function(jqXhr, textStatus, errorThrown){
                alert("Error al cargar datos");
            }
        });
    }
</script>
</html>


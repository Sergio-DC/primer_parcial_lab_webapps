<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	function eliminar(that) {
		var requestedData = "comentarioBean.id_comentario=" + that.value;
		console.log("Push: " + that.value);
		$.getJSON("eliminar_contenido", requestedData, function(data) {
			$.each(data.registros_eliminados, function(index, id_comentario) {//N�mero de Comentario que se eliminar�
				console.log("ID de Comentario eliminado: " + id_comentario);
				var row = $('.bEliminar[value='+id_comentario+']').parent().parent();
				$(row).hide();
			});

		});
	}
</script>

</head>
<body style="margin-left: 20px;">
	<h1>Struts2 and jQuery JSON integration Example</h1>
	<form action="countrystate" method="post">
		<input type="submit" value="Click Me" />
	</form>
	<div style="margin-top: 10px;">		
		<table border="1">
		<tr>
			<td><b>id_comentario</b></td>		
			<td><b>id_usuario</b></td> 
		    <td><b>nombre</b></td>	       
	       <td><b>fecha_publicacion</b></td>
	       <td><b>contenido</b></td>					  
	       <td><b>id_respuesta_a</b></td>
	       <td><b>Eliminar</b></td>
	    </tr>
		<s:iterator value="lista_comentario_usuario" status="i">
			<tr>
				<td><s:property value="id_comentario"/></td>
				<td><s:property value="id_usuario"/></td>
				<td><s:property value="nombre"/></td>
				<td><s:property value="fecha_publicacion"/></td>
				<td><s:property value="contenido"/></td>								
				<td><s:property value="id_respuesta_a"/></td>
				<td><button class="bEliminar" value="${id_comentario}" onclick="eliminar(this)">Eliminar</button></td>
			</tr>
		</s:iterator>
		<!-- State:
		<select id="stateval">
			<option>Select State</option>
		</select> -->
	</div>
</body>
</html>
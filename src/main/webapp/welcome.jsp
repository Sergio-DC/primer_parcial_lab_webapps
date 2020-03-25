<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="js/jquery.js"></script>
<!-- <script type="text/javascript">
	function reflect(that) {

		var countryName = $(that, "#countryVal").val();
		var requestedData = "countryVal=" + countryName;
		console.log("Req Data: " + requestedData);
		$.getJSON("stateviacountry", requestedData, function(data) {
			var sparrow = "";
			console.log("datos:" + data);
			$.each(data.dtoList, function() {

				sparrow += "<option>" + this.state + "</option>";

			});
			sparrow += "";

			$("#stateval").html(sparrow);
		});
	}
</script> -->

</head>
<body style="margin-left: 20px;">
	<h1>Struts2 and jQuery JSON integration Example</h1>
	<form action="countrystate" method="post">
		<input type="submit" value="Click Me" />
	</form>
	<div style="margin-top: 10px;">		
		<table border="1">
		<tr>		
			<td><b>id_usuario</b></td> 
		    <td><b>nombre</b></td>    
	       <td><b>id_comentario</b></td>
	       <td><b>fecha_publicacion</b></td>
	       <td><b>contenido</b></td>					  
	       <td><b>id_respuesta_a</b></td>
	    </tr>
		<s:iterator value="lista_comentario_usuario" status="i">
			<tr>
				<td><s:property value="id_usuario"/></td>
				<td><s:property value="nombre"/></td>
				<td><s:property value="id_comentario"/></td>
				<td><s:property value="fecha_publicacion"/></td>
				<td><s:property value="contenido"/></td>								
				<td><s:property value="id_respuesta_a"/></td>
				<button class="bEliminar" onclick="eliminar(this)">Eliminar</button>
			</tr>
		</s:iterator>
		<!-- State:
		<select id="stateval">
			<option>Select State</option>
		</select> -->
	</div>
</body>
</html>
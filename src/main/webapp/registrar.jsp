<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registrar Nuevo Usuario</title>
</head>
<body>
	<h1>Registrar Usuario</h1>
	<s:form action="registrar">
	 	  <s:textfield name="usuarioBean.nombre" label="Usuario" />
	 	  <s:textfield  name="usuarioBean.pass" label="Password" />	
	 	  <s:textfield  name="confirmar_pass" label="Confirmar Password" /> 	  
	   	  <s:submit/>
	</s:form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<h1>Completa los campos</h1>
	<s:form action="login">

	 	  <s:textfield name="usuarioBean.nombre" label="Usuario" />
	 	  <s:textfield  name="usuarioBean.pass" label="Password" />	 		 	  
	   	  <s:submit/>
	</s:form>
</body>
</html>
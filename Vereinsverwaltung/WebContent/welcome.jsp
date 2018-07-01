<%@page import="com.sw.servlets.Login"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="css/layout.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<style>
	#button{
	color: white;
	font-size: 20px;
	padding: 1% 10%;
	margin-top: 5%;
	border: none;
	cursor: pointer;
	border-radius: 5px;
	}
</style>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0);
pageContext.setAttribute("infoMessage", Login.getInfoMessage());
%>
	
<title>Willkommen</title>
</head>
<body>
<header>
	<h1>Vereinsverwaltung</h1>
</header>
<div class="content-wrap">
<form action="${pageContext.request.contextPath}/welcome" method="post">
	<div class="form-group w-75">
		<label for="exampleInputInput1">Benutzername</label>
		<input type="text" name="username" class="form-control" id="formGroupExampleInput" placeholder="Benutzernamen eingeben">
		<small id="emailHelp" class="form-text text-muted">Beispiel: tkolb</small>
	</div><br>
	<div class="form-group w-75">
		<label for="formGroupExampleInput2">Passwort</label>
    	<input type="password" name="pwd" class="form-control" id="formGroupExampleInput2" placeholder="Passwort eingeben">
	</div>	
	<button class="btn btn-primary" id="button" type="submit">login</button>
	<small id="emailHelp" class="form-text text-muted">${infoMessage}</small>
</form>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</body>
</html>
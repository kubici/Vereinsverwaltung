<%@page import="com.sw.servlets.Login"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="css/welcome.css">
<link rel="stylesheet" href="css/layout.css">
<link rel="stylesheet" href="css/fonts.css">
<link rel="stylesheet" href="css/bootstrap-4/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0);

	// TODO Is there a possibility to check if the page was refreshed?
	//TODO  It can be done with javascript, but jsp would be better!
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
	<div class="form-group">
		<label for="exampleInputInput1">Benutzername</label>
		<input type="text" name="username" class="form-control" id="formGroupExampleInput" placeholder="Benutzernamen eingeben">
		<small id="emailHelp" class="form-text text-muted">Beispiel: tkolb</small>
	</div><br>
	<div class="form-group">
		<label for="formGroupExampleInput2">Passwort</label>
    	<input type="password" name="pwd" class="form-control" id="formGroupExampleInput2" placeholder="Passwort eingeben">
	</div>	
	<button class="btn btn-primary" id="login_button" type="submit">login</button>
	<small id="emailHelp" class="form-text text-muted">${infoMessage}</small>
</form>
</div>
</body>
</html>
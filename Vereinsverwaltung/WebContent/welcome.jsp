<%@page import="com.sw.servlets.Login"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Willkommen</title>
<link href="css/layout.css" rel="stylesheet" type="text/css">
<link href="css/fonts.css" rel="stylesheet" type="text/css">
<link href="css/welcome.css" rel="stylesheet" type="text/css">
</head>
<body>
<header>
	<h1>Vereinsverwaltung</h1>
</header>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0);
 %>

<div class="content-wrap">
		<form action="${pageContext.request.contextPath}/welcome" method="post">
			
			username <input type="text" name="username"><br>
			password <input type="password" name="pwd">	
						
			<input id="login_button" type="submit" value="login">	
		</form>
</div>

<%
	// TODO Is there a possibility to check if the page was refreshed?
	//TODO  It can be done with javascript, but jsp would be better!
	pageContext.setAttribute("infoMessage", Login.getInfoMessage());
%>
<c:out value="${infoMessage}"></c:out>

</body>
</html>
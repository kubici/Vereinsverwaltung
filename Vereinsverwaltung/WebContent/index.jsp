<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@page import="com.sw.beans.Member"  %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/layout.css">
<link rel="stylesheet" href="css/fonts.css">
<link rel="stylesheet" href="bootstrap-4/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<title>Homepage</title>
</head>
<body>
<header>
	<h1>Vereinsverwaltung</h1>
	
	<% Member currentUser = (Member) session.getAttribute("currentUser");
		pageContext.setAttribute("currentUser", currentUser); %>
	
<!-- Check for a valid session -->

<% 
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0);
if(request.getSession().getAttribute("currentUser") == null)
	{
		System.out.println("No Session");
		response.sendRedirect("./welcome.jsp");
	}	
	else
	{
	System.out.println("Session alive!");
	System.out.println(request.getSession().getAttribute("currentUser"));
	}
%>

	
<% // TODO Dropdown-Menü mit Einstellungs-Icon %>
	<div class="settings">
	<p id="user_label">Benutzer: <c:out value="${currentUser.username}"/></p>
	<p>
		<a href="changePassword.jsp">
		<input class="button" type="submit" value="Passwort ändern"/>
		</a>
	</p>
	<form action="${pageContext.request.contextPath}/Logout" method="post">
		<input class="button" type="submit" value="Logout"/>
	</form>
	</div>
</header>

	
<div class="content-wrap">
	<p>This is the Testoverview</p>

	<a href="member.jsp">
		<input class="module" type="submit" value="Mitglieder Overview"/>
	</a>
</div>
</body>
</html>
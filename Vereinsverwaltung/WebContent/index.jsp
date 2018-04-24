<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@page import="com.sw.beans.User"  %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Homepage</title>
<link href="css/fonts.css" rel="stylesheet" type="text/css">
<link href="css/layout.css" rel="stylesheet" type="text/css">
<link href="css/index.css" rel="stylesheet" type="text/css">
</head>
<body>
<header>
	<h1>Vereinsverwaltung</h1>
	
	<% // TODO Dropdown-Menü mit Einstellungs-Icon %>
	<div class="settings">
	<p id="user_label">Benutzer: <c:out value="${currentUser.uname}"/></p>
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

<% User currentUser = (User) session.getAttribute("currentUser");
	pageContext.setAttribute("currentUser", currentUser); %>
	
<div class="content-wrap">
	<p>This is the Testoverview</p>

	<a href="member.jsp">
		<input class="module" type="submit" value="Mitglieder Overview"/>
	</a>
</div>
</body>
</html>
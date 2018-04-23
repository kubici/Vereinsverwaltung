<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@page import="com.sw.beans.User"  %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Homepage of your Website</title>
</head>
<body>

<!-- Check for a valid session -->

<% 
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
if(request.getSession().getAttribute("currentUser") == null)
	{
		System.out.println("keine Session");
		response.sendRedirect("./welcome.jsp");
	}	
	else
	{
	System.out.println("Session vorhanden!");
	System.out.println(request.getSession().getAttribute("currentUser"));
	}
 %>


		<p>This is the Testoverview</p>
		<br>
		
		<% User currentUser = (User) session.getAttribute("currentUser");
			pageContext.setAttribute("currentUser", currentUser); %>
			
			
		<p>Benutzer: <c:out value="${currentUser.uname}"/></p>
		<br>
		<br>
		<br>
		<a href="mitglieder.jsp">
		<input type="submit" value="Mitglieder Overview"/>
		</a>
		
	<seciont id="Logout">
		<form action="${pageContext.request.contextPath}/Logout" method="post">
		<input type="submit" value="Lougout"/>
		</form>
	</seciont>
		
</body>
</html>
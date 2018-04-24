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
		<p>
			<a href="changePassword.jsp">
				<input type="submit" value="Passwort ändern"/>
			</a>
		</p>

		<p>This is the Testoverview</p>
		<br>
		
		<% User currentUser = (User) session.getAttribute("currentUser");
			pageContext.setAttribute("currentUser", currentUser); %>
			
			
		<p>Benutzer: <c:out value="${currentUser.uname}"/></p>
		<br>
		<br>
		<br>
		<a href="member.jsp">
		<input type="submit" value="Mitglieder Overview"/>
		</a>
		
		<form action="${pageContext.request.contextPath}/Logout" method="post">
		<input type="submit" value="Logout"/>
		</form>
		
</body>
</html>
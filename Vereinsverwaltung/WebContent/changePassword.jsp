<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Passwort ändern</title>
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

	<form action="${pageContext.request.contextPath}/changePWD" method="post">
		
		Altes Passwort <input type="password" name="pwd_old"><br>
		Neues Passwort <input type="password" name="pwd_new01"><br>
		Passwort wiederholen <input type="password" name="pwd_new02"><br>	
		<input id="submit_btn" type="submit" value="Passwort ändern">
			
	</form>
</body>
</html>
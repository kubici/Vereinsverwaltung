<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Homepage of your Website</title>
</head>
<body>
		<p>This is the Testoverview</p>
		
<!-- 	<form action="${pageContext.request.contextPath}/MitgliederServlet" method="post"> 
		<form action="mitgliederView.jsp" method="post">
			<input type="submit" value="Mitglieder">
		
		</form>
		-->
		<a href="mitgliederView.jsp">
		<input type="submit" value="Mitglieder"/>
		</a>
		<br>
		<a href="mitgliederHome.jsp">
		<input type="submit" value="Mitglieder Overview"/>
		</a>
		
</body>
</html>
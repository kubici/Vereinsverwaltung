<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page 2</title>
</head>
<body>

<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
 %>

<section id ="welcome">
		<form action="${pageContext.request.contextPath}/welcome" method="post">
			Enter username: <input type="text" name="username"><br>
			Enter password: <input type="password" name="pwd"><br>
			<input type="submit" value="login">
		</form>
</section>

</body>
</html>
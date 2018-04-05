<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page 2</title>
</head>
<body>

<section id ="Login">
		<form action="${pageContext.request.contextPath}/Login" method="post">
			Enter username: <input type="text" name="uname"><br>
			Enter password: <input type="password" name="pass"><br>
			<input type="submit" value="login">
		</form>
</section>

</body>
</html>
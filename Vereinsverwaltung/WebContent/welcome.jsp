<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page 2</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>

<header>
	<h1>Vereinsverwaltung</h1>
</header>
<body>
<%response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0);
 %>

<div class="content-wrap">
<section id ="welcome">
		<form action="${pageContext.request.contextPath}/welcome" method="post">
		
			<input id="login_button" type="submit" value="login">
			
			username <input type="text" name="username"><br>
			password <input type="password" name="pwd">		
		</form>
</section>
</div>

</body>
</html>
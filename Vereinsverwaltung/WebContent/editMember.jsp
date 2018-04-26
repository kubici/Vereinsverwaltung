<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h2>Edit the Member or generate a new password</h2>
		
		<form action="${pageContext.request.contextPath}/saveMember" method="post">
		
		<input type="text" name="username" value='<%=request.getAttribute("username")%>' readonly/><br>
		<input type="text" name="first_name" value='<%=request.getAttribute("first_name")%>'/><br>
		<input type="text" name="last_name"  value='<%=request.getAttribute("last_name")%>'/><br>
		<p>Geburtstag: (Format dd.MM.yyyy) <br>
			<input type="date" name="birth_date" value='<%=request.getAttribute("birth")%>'/><br>
		</p>
			<input type="radio" name="gender" value="male"> Male<br>
 			<input type="radio" name="gender" value="female"> Female<br>
 			<input type="radio" name="gender" value="other"> Other <br>
		<input type="email" name="email_address" value='<%=request.getAttribute("mail")%>' /><br>
		<input type="tel" name="phone_number" value='<%=request.getAttribute("phone")%>'/><br>
		<p>
			Adresse: <br>
			<input type="text" name="address_line" value='<%=request.getAttribute("address")%>'/><br>
			<input type="text" name="address_add" value='<%=request.getAttribute("address_add")%>' /><br>
			<input type="text" name="post_code" value='<%=request.getAttribute("post_code")%>'/>
			<input type="text" name="city" value='<%=request.getAttribute("city")%>'/><br>
		</p>
		<input type="checkbox" name="password_change" value='<%=request.getAttribute("password")%>'><%=request.getAttribute("password")%><br>
		<input id="submit_btn" type="submit" value="Submit">
			
		</form>
		
		
		
</body>
</html>
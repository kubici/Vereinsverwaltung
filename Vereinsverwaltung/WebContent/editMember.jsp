<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<h2>Mitglied bearbeiten</h2>
		
		<form action="${pageContext.request.contextPath}/saveMember" method="post">
		Aktueller username: <%= request.getAttribute("username") %><br>
		<input type="hidden" name="username" value='<%=request.getAttribute("username")%>' /><br>
		<input type="text" name="first_name" value='<%=request.getAttribute("first_name")%>'/><br>
		<input type="text" name="last_name"  value='<%=request.getAttribute("last_name")%>'/><br>
		<p>Geburtstag: (Format dd.mm.yyyy) <br>
			<input type="date" name="birth_date" value='<%=request.getAttribute("birth")%>'/><br>
		</p>
		<p>
		Geschlecht:<br>
			<input type="radio" name="gender" value="male"  <%= request.getAttribute("gender").equals("male") ?  "checked" : "" %> /> Male<br>
 			<input type="radio" name="gender" value="female"  <%= request.getAttribute("gender").equals("female") ?  "checked" : "" %>/> Female<br>
 			<input type="radio" name="gender" value="other"  <%= request.getAttribute("gender").equals("other") ?  "checked" : "" %>/> Other <br>
 		</p>
		<input type="email" name="email_address" value='<%=request.getAttribute("mail")%>' /><br>
		<input type="tel" name="phone_number" value='<%=request.getAttribute("phone")%>'/><br>
		<p>
			Adresse: <br>
			<input type="text" name="address_line" value='<%=request.getAttribute("address")%>'/><br>
			<input type="text" name="address_add" value='<%=request.getAttribute("address_add")%>' /><br>
			<input type="text" name="post_code" value='<%=request.getAttribute("post_code")%>'/>
			<input type="text" name="city" value='<%=request.getAttribute("city")%>'/><br>
		</p>
		<p>
			Um ein neues Passwort zu generieren Checkbox benutzen. Ansonsten wird das alte Passwort beibehalten! <br>
			<input type="checkbox" name="password_change" value='<%=request.getAttribute("password")%>'><%=request.getAttribute("password")%><br>
			<input id="submit_btn" type="submit" value="Submit">
		</p>
		</form>
		
		
		
</body>
</html>
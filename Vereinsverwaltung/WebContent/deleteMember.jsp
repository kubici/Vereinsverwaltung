<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Delete the Member</h2>
		
		<form action="${pageContext.request.contextPath}/deleteMember" method="post">
		
	
		
	<input type="text" name="memberId" value='<%=request.getAttribute("memberId")%>' readonly/><br>
	<input type="checkbox" name="memberId" value='<%=request.getAttribute("memberId")%>'><br>
	<input id="delete_btn" type="submit" value="Delete">
	
	</form>
		
</body>
</html>
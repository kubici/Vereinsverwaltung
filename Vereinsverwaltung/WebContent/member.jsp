<%@page import="com.sw.controller.MemberDashboardController"%>
<%@page import="com.sw.beans.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

 <!-- Loading MitgliederList from Controller -->
	<% 
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");	
	MemberDashboardController controller = new MemberDashboardController();
	pageContext.setAttribute("mList", controller.getLstMember());
		
	 %>
		 
<html>
<head>

	<title>Mitgliederverwaltung</title>

</head>
<body>
	<h2>Test</h2>


	
	<p>Next Table</p>
	
	
	<jsp:useBean id="user" class="com.sw.beans.User"></jsp:useBean>
	<c:out value="${user.uname}"></c:out>
	

	 <!-- Here you can see a table of all mitglieder in your team -->
	<jsp:useBean id="mitgliederList" class="com.sw.controller.MemberDashboardController"></jsp:useBean>
	
	<table border="3">
	<th>Name</th>
	<th>Lname</th>
	<c:forEach items="${mList}" var="mList">
	<tr>
		<td><c:out value="${mList.name}"></c:out></td>
		<td><c:out value="${mList.lname}"></c:out></td>
	</tr>
	</c:forEach>
	</table>
	
	
	 <!-- Here is the part for register new Mitglieder -->
	<form action="registerMember.jsp">

	<br/>
	<br/>
	Click for register new Mitglieder
	<input type = "submit" value = "+"/>
	
	
</body>
</html>
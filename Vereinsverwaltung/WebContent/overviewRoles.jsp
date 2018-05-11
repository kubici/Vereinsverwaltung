<%@page import="com.sw.dao.RoleDao"%>
<%@page import="com.sw.beans.Role" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/layout.css">
<link rel="stylesheet" href="css/fonts.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
 
<!-- Loading MitgliederList from Controller -->
<% 
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");	
RoleDao roledao = new RoleDao();
pageContext.setAttribute("rList", roledao.getRoles());
%>
<title>Rollenverwaltung</title>
</head>
<body>

	<h2>Rollenübersicht</h2>
	
	 <!-- Here you can see a table of all roles in your team -->
	<jsp:useBean id="roleList" class="com.sw.dao.RoleDao"></jsp:useBean>

<div class="content-wrap">
	<table border="3">
		<th>Beschreibung</th>
		<th>Rolle bearbeiten</th>
		<th>Rolle löschen</th>
		<c:forEach items="${rList}" var="rList" varStatus="loop">
		<tr>
			<td><c:out value="${rList.role_description}"></c:out></td>
			<td>
				<!-- This form is needed to get the selected item -->
				<form action="editRole.jsp" method="post">
		   				<button class="button" type="submit" name="id" value="${rList.role_id}" style="background-color:transparent; border-color:transparent;">
		   					<img src="./image/edit_icon.png" alt="Hallo" style="width:32px;height=32px; border=0"/>
		   				</button>
		   				<input type="hidden" name="role_id" value="${rList.role_id}">
		   				<input type="hidden" name="role_description" value="${rList.role_description}">
				</form>
			</td>
			<td>
				<form action="deleteRole" method="post"  onsubmit="return buttonPressed();">
		   				<button class="button" id="deleteButton" type="submit" name="id" value="${rList.role_id}" style="background-color:transparent; border-color:transparent;">
		   					<img src="./image/delete_icon.png" style="width:32px;height=32px; border=0"/>
		   				</button>
				</form>
			</td>
		</tr>
		</c:forEach>
	</table>
	
	<!-- Controll delete Button -->
	<script type="text/javascript">
		function buttonPressed()
		{		
			answer = confirm("Rolle löschen?");
			if(answer == true)
			{
		   			return true;
			}
			else if(answer == false)
			{
				alert("Löschvorgang abgebrochen!");
				return false;
			}
		}
	</script>
	
	<!-- Here is the part for register new roles -->
	<form action="addRole.jsp">		
		<br/>
		<br/>
		Neues Rolle hinzufügen
		<input type = "submit" value = "+"/>
	</form>
</div>
</body>
</html>
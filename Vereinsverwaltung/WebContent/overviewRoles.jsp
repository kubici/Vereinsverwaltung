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

<!-- NAVBAR -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="index.jsp">
		<img src="image/group_icon.png" width="30" height="30" class="d-inline-block" alt="">
		Vereinsverwaltung
	</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	  <span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">	
<!-- 	NAVBAR-ITEM -->
		<li class="nav-item active">
	        <a class="nav-link btn btn-light text-left pl-2" href="./MemberDashboardServlet">
	        	Mitglieder 
			</a>
	    </li>
   		<li class="nav-item disabled">
			<a class="nav-link btn btn-light text-left pl-2">
				Rollen 
				<span class="sr-only">(current)</span>
			</a>
	    </li>
   		<li class="nav-item active">
			<a class="nav-link btn btn-light text-left pl-2" href="./InventoryDashboardServlet">
				Inventar 
			</a>
	    </li>	    	    
<!-- 	    DISABLED NAVBAR-ITEM -->
<!-- 	    <li class="nav-item"> -->
<!-- 		<a class="nav-link disabled" href="#">Disabled</a> -->
<!-- 	    </li> -->
      
		</ul>
<!-- 	DROPDOWN-MENU NAVBAR -->
		<div class="nav-item dropdown">
	    	<a class="nav-link btn btn-light dropdown-toggle text-left" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				<img src="image/settings_icon.png" width="25" height="25" class="d-inline-block p-0" alt="">
			</a>
			<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
				<h6 class="dropdown-header">Einstellungen</h6>
				<a class="dropdown-item" id="user_label">
					Benutzer: <c:out value="${currentUser.username}"/></a>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item" href="changePassword.jsp">
					<input class="btn btn-secondary btn-block" type="submit" value="Passwort ändern"/></a>
				<a class="dropdown-item" href="#">
					<form action="${pageContext.request.contextPath}/Logout" method="post">
						<input class="btn btn-secondary btn-block" type="submit" value="Logout"/>
					</form>
				</a>
			</div>
		</div>
	</div>
</nav>
</head>
<body>

	<h1>Rollenübersicht</h1>
	
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
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>	
</body>
</html>
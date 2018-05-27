<%@page import="com.sw.dao.ModuleDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sw.dao.RoleModuleAccessDao"%>
<%@page import="com.sw.beans.Module" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/layout.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

<% 
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");	
ModuleDao moduledao = new ModuleDao();
pageContext.setAttribute("mList", moduledao.getModules());
RoleModuleAccessDao accessdao = new RoleModuleAccessDao();
ArrayList<Integer> list = accessdao.getModuleIdByRoleId(Integer.parseInt(request.getParameter("role_id")));
%>

<title>Rolle bearbeiten</title>

<!-- NAVBAR -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="./DashboardServlet">
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
	    	<a class="nav-link btn btn-light text-left pl-2" href="./MemberServlet">
	       	Mitglieder 
			</a>
	    </li>
   		<li class="nav-item active">
			<a class="nav-link btn btn-light text-left pl-2" href="./RoleServlet">
			Rollen 
			</a>
	    </li>
   		<li class="nav-item active">
			<a class="nav-link btn btn-light text-left pl-2" href="./InventoryServlet">
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
				<a class="dropdown-item" href="./ChangePasswordServlet">
					<input class="btn btn-primary btn-block" type="submit" value="Passwort ändern"/></a>
				<a class="dropdown-item" href="#">
					<form action="${pageContext.request.contextPath}/Logout" method="post">
						<input class="btn btn-primary btn-block" type="submit" value="Logout"/>
					</form>
				</a>
			</div>
		</div>
	</div>
</nav>
</head>
<body>

<h2>Rolle bearbeiten</h2>

<div class="content-wrap">
	<fieldset class="mb-5 border p-4">
	<form action="${pageContext.request.contextPath}/editRole" method="post">
		<div class="form-group">
			<input type="hidden" class="form-control" name="role_id" value=<%=request.getParameter("role_id") %> />
			<input type="text" class="form-control" name="role_description" value=<%=request.getParameter("role_description") %> /><br>
		</div>
		<div class="form-group">
		<label for="formGroupExampleInput">Zugangsberechtigung</label>
		<c:forEach items="${mList}" var="mList" varStatus="loop">
			<div class="form-check">
				<input type="checkbox" class="form-check-input" id="defaultCheck1" name="role_module_access" value="${mList.module_id}" <%=list.contains( ((Module) pageContext.getAttribute("mList")).getModule_id()) ? "checked" : "" %>/>
				<label class="form-check-label" for="defaultCheck1">
					<c:out value="${mList.module_description}"></c:out> 
				</label>
				<br>
			</div>
		</c:forEach>
		<small class="form-text text-muted">
			Die Rolle eines Mitglieds stellt fest, welche Zugangsberechtigungen<br>
			in welche Module Zugriff haben.
		</small>
		</div>
		<%//TODO fix Änderungen speichern %>
		<input id="submit_btn" class="btn btn-primary" type="submit" value="Änderungen speichern">
		<a class="btn btn-primary" href="./RoleServlet" role="button">Abbrechen</a>
	</form>
	</fieldset>
</div>

<!-- Override Button-Colors -->
<style>
	.btn-primary,
	.btn-primary:hover,
	.btn-primary:active,
	.btn-primary:visited,
	.btn-primary:focus {
	    background-color: #F44336 !important;
	    border-color: #F44336 !important;
		}
</style>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>	
</body>
</html>
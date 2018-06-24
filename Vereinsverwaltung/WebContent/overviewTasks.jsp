<%@page import="com.sw.dao.RoleDao"%>
<%@page import="com.sw.beans.Role" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.sw.dao.ModuleDao"%>
<%@page import="com.sw.dao.TaskDao"%>
<%@page import="com.sw.beans.Task"%>
<%@page import="com.sw.beans.Module" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/layout.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
 
<% 
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");	

response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");	

%>
<title>Aufgaben</title>

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
			<!--NAVBAR-ITEM -->
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
	   		<li class="nav-item disabled">
				<a class="nav-link btn btn-light text-left pl-2">
					Aufgaben
					<span class="sr-only">(current)</span>
				</a>
		    </li>    	    
		</ul>
		<!--DROPDOWN-MENU NAVBAR -->
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
<header>
<h1 style="color:#00C85A !important">Aufgabenübersicht</h1>
</header>

<%
TaskDao taskDao = new TaskDao();
pageContext.setAttribute("tList", taskDao.getTasks());
 %>

 <!-- TaskCreationServlet muss noch erzeugt werden -->
 <!-- 
<form action="TaskCreationServlet" method="post" >
<button class="button" id="createButton" type="submit" name="id" style="background-color:transparent; border-color:transparent;">
<img src="./image/edit_icon.png" style="width:32px;height=32px; border=0"/>
</button>
</form>
-->

<!-- List of Tasks -->
<jsp:useBean id="taskList" class="com.sw.dao.TaskDao"></jsp:useBean>

<div class="content-wrap">
	<div class="table-responsive-lg">
<!-- NEW TASK FORMULAR -->
		<div class="collapse" id="collapse_addTask">
			<fieldset class="my-5 border p-4">
			<h3>neue Aufgabe erstellen</h3><br/>
				<form action="${pageContext.request.contextPath}/addTask" method="post">
					<div class="form-group">
						<input type="text" class="form-control" name="task_creator" value="${currentUser.username}" hidden/>
						<input type="text" class="form-control" name="task_title" placeholder="Aufgaben-Titel"/>
					</div>
					<div class="form-group">
						<label for="comment">Beschreibung:</label>
	  					<textarea class="form-control" rows="5" name="task_text"></textarea>
					</div>
					<button type="submit" class="btn btn-primary" name="submit_mitglied">Aufgabe erstellen</button>
					<button type="reset" class="btn btn-primary" name="submit_mitglied" data-toggle="collapse" href="#collapse_addTask" role="button" aria-expanded="false" aria-controls="collapse_addTask">Abbrechen</button>
				</form>
			</fieldset>
		</div>
		<button type="button" id="collapse-button" class="btn btn-primary btn-lg btn-block" data-toggle="collapse" href="#collapse_addTask" role="button" aria-expanded="false" aria-controls="collapse_registerMember">
			<img src="./image/add_icon_white.png" height="25"></img>
		</button>
		<table class="table table-hover mt-3">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Erledigt</th>
					<th scope="col">Titel</th>
					<th scope="col">Löschen</th>
					<th scope="col">Erledigen</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${tList}" var="tList" varStatus="loop">
					<tr>
						<td><c:out value="${tList.taskId}">></c:out></td>
						<td><c:out value="${tList.completed}">></c:out></td>
						<td><c:out value="${tList.titel}">></c:out></td>
						<td>
							<form action="deleteTask" method="post"  onsubmit="return buttonPressed();">
			   				<button class="button btn-danger" id="deleteButton" type="submit" name="id" value="${tList.taskId}" style="background-color:transparent; border-color:transparent;">
			   					<img src="./image/deletetask_64.png" style="width:32px;height=32px; border=0"/>
			   				</button>
							</form>
						</td>
						<td>
							<form action="TaskCompletedServlet" method="post" >
			   				<button class="button btn-success" id="checkButton" type="submit" name="id" value="${tList.taskId}" style="background-color:transparent; border-color:transparent;">
			   					<img src="./image/checktask_64.png" style="width:32px;height=32px; border=0"/>
			   				</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<style>
	.btn-primary,
		.btn-primary:active,
		.btn-primary:visited,
		.btn-primary:focus
	{
	    background-color: #00C85A !important;
	    border-color: #00C85A !important;
	}
	.btn-primary:hover{
		background-color: #66ffab !important;
		border-color: #66ffab !important;
	}
	
	#collapse-button{
	border-radius: 15px;
	}
</style>

<!-- Controll delete Button -->
<script type="text/javascript">
	function buttonPressed()
	{		
		answer = confirm("Aufgabe löschen?");
		if(answer == true)
		{
	   		return true;
		}
		else if(answer == false)
		{
			return false;
		}
	}
</script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>	
</body>
</html>
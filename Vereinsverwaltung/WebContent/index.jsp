<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@page import="com.sw.beans.Member"  %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/module.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<title>Homepage</title>

<% Member currentUser = (Member) session.getAttribute("currentUser");
	pageContext.setAttribute("currentUser", currentUser); %>
	
<!-- Check for a valid session -->
<% 
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0);
%>
	<!-- NAVBAR -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="./DashboardServlet" >
			<img src="image/group_icon.png" width="30" height="30" class="d-inline-block" alt="">
			Vereinsverwaltung
		</a>
		<div class="navbar-nav mr-auto"></div>
	<!-- 	DROPDOWN-MENU NAVBAR -->
		<div class="nav-item dropdown">
	    	<a class="nav-link btn btn-light dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				<img src="image/settings_icon.png" width="25" height="25" class="d-inline-block p-0" alt="">
			</a>
			<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
				<h6 class="dropdown-header">Einstellungen</h6>
				<a class="dropdown-item" id="user_label">
					Benutzer: <c:out value="${currentUser.username}"/></a>
				<div class="dropdown-divider"></div>
				<a class="dropdown-item">
					<form action="${pageContext.request.contextPath}/ChangePasswordServlet" method="post">
						<input class="btn btn-primary btn-block" type="submit" value="Passwort ändern"/></a>
					</form>
				<a class="dropdown-item">
					<form action="${pageContext.request.contextPath}/Logout" method="post">
						<input class="btn btn-primary btn-block" type="submit" value="Logout"/>
					</form>
				</a>
			</div>
		</div>
	</nav>
</head>
<body>
<header>
<h1>Herzlich Willkommen!</h1>
</header>
<div class="content-wrap">
	<div class="container">
		<div class="row mx-0">
			<div class="col p-0">
				<a class="module" href="./MemberDashboardServlet">
					<div id="card_img1"></div>
					<div id="card_content">
					  <h2>Mitglieder</h2>
				  </div>  
				</a>
			</div>
			<div class="col p-0">
				<a class="module" href="./RoleServlet">
					<div id="card_img2"></div>
					<div id="card_content">
						<h2>Rollen</h2>  
			  		</div>  
				</a>        
			</div>
			<div class="col p-0">
				<a class="module" href="./InventoryDashboardServlet">
					<div id="card_img3"></div>
					<div id="card_content">
					  <h2>Inventar</h2>
				  </div>  
				</a>        
			</div>
			<div class="col p-0">
				<a class="module" href="#">
					<div id="card_img4"></div>
					<div id="card_content">
					  <h2>Finanzen</h2>
				  </div>  
				</a>        
			</div>			
		</div>
	</div>
</div>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
<script src="js/module.js"></script>
</body>
</html>
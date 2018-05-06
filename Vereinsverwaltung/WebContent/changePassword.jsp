<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/layout.css">
<link rel="stylesheet" href="css/fonts.css">
<link rel="stylesheet" href="css/welcome.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

<title>Passwort �ndern</title>
</head>
<body>
<header>

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
<!-- 		NAVBAR-ITEM -->
			<li class="nav-item active">
		        <a class="nav-link" href="overviewMember.jsp">
		        	Mitglieder 
				</a>
		    </li>
	   		<li class="nav-item active">
				<a class="nav-link" href="#">
					Rollen 
				</a>
		    </li>	    
<!-- 	    DISABLED NAVBAR-ITEM -->
<!-- 	    <li class="nav-item"> -->
<!-- 		<a class="nav-link disabled" href="#">Disabled</a> -->
<!-- 	    </li> -->
			</ul>
			<div class="nav-item dropdown">
		    	<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
					<img src="image/settings_icon.png" width="20" height="20" class="d-inline-block" alt="">
				</a>
				<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" id="user_label">
						Benutzer: <c:out value="${currentUser.username}"/></a>
					<a class="dropdown-item" href="changePassword.jsp">
						<input class="button" type="submit" value="Passwort �ndern"/></a>
					<a class="dropdown-item" href="#">
						<form action="${pageContext.request.contextPath}/Logout" method="post">
							<input class="button" type="submit" value="Logout"/>
						</form>
					</a>
				</div>
			</div>
	</div>
</nav>
<h2>Password �ndern</h2>
</header>

<!-- Check for a valid session -->
<% 

response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0);
if(request.getSession().getAttribute("currentUser") == null)
	{
		System.out.println("No Session");
		response.sendRedirect("./welcome.jsp");
	}	
	else
	{
	System.out.println("Session alive!");
	System.out.println(request.getSession().getAttribute("currentUser"));
	}
%>

<div class="content-wrap">
	<div class="container">
		<form action="${pageContext.request.contextPath}/changePWD" method="post">
			<div class="form-group w-75">
				<label for="exampleInputPassword1">altes Passwort</label>
	  			<input type="password" name="pwd_old" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="altes Passwort eingeben">
			</div>
			<div class="form-group w-75">
	  			<label for="exampleInputPassword1">neues Passwort</label>
	  			<input type="password" name="pwd_new01" class="form-control" id="exampleInputPassword1" placeholder="neues Password eingeben">
			</div>		
			<div class="form-group w-75">
	  			<input type="password" name="pwd_new02" class="form-control" id="exampleInputPassword1" placeholder="neues Password wiederholen">
			</div>
			<button type="submit" class="btn btn-primary">Passwort �ndern</button>
		</form>
	</div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</body>
</html>
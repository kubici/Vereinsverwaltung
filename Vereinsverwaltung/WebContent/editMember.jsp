<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/layout.css">
<link rel="stylesheet" href="css/fonts.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

<title>Mitglieder</title>
</head>
<body>
<header>
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
	        <a class="nav-link" href="./MemberDashboardServlet">
	        	Mitglieder 
<!-- 	        	<span class="sr-only">(current)</span> -->
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
				<a class="dropdown-item" href="./ChangePasswordServlet">
					<input class="button" type="submit" value="Passwort ändern"/></a>
				<a class="dropdown-item" href="#">
					<form action="${pageContext.request.contextPath}/Logout" method="post">
						<input class="button" type="submit" value="Logout"/>
					</form>
				</a>
			</div>
		</div>
	</div>
</nav>			
</header>
<h2>Mitglied bearbeiten</h2>
		
<div class="content-wrap">
		<form action="${pageContext.request.contextPath}/saveMember" method="post">
		Benutzername: <%= request.getAttribute("username") %><br>
		<input type="hidden" name="username" value='<%=request.getAttribute("username")%>' /><br>
		<input type="text" name="first_name" value='<%=request.getAttribute("first_name")%>'/><br>
		<input type="text" name="last_name"  value='<%=request.getAttribute("last_name")%>'/><br>
		<p>Geburtstag: (Format dd.mm.yyyy) <br>
			<input type="date" name="birth_date" value='<%=request.getAttribute("birth")%>'/><br>
		</p>
		<p>
		Geschlecht:<br>
			<input type="radio" name="gender" value="male"  <%= request.getAttribute("gender").equals("male") ?  "checked" : "" %> /> Männlich<br>
 			<input type="radio" name="gender" value="female"  <%= request.getAttribute("gender").equals("female") ?  "checked" : "" %>/> Weiblich<br>
 			<input type="radio" name="gender" value="other"  <%= request.getAttribute("gender").equals("other") ?  "checked" : "" %>/> Neutral <br>
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
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</body>
</html>
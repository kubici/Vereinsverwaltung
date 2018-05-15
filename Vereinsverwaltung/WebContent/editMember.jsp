<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/layout.css">
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
	    <a class="nav-link btn btn-light text-left pl-2" href="./MemberDashboardServlet">
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
					<input class="btn btn-secondary" type="submit" value="Passwort ändern"/></a>
				<a class="dropdown-item" href="#">
					<form action="${pageContext.request.contextPath}/Logout" method="post">
						<input class="btn btn-secondary" type="submit" value="Logout"/>
					</form>
				</a>
			</div>
		</div>
	</div>
</nav>			
</header>
<h2>Mitglied bearbeiten</h2>

		
<%//TODO collapsing-form-integration ??? %>
<div class="content-wrap">
	<fieldset class="mb-5 border p-4">
		<form action="${pageContext.request.contextPath}/saveMember" method="post">
		
		<h4>Benutzer: <%= request.getAttribute("username") %></h4><br/>
		
		<div class="form-group">
			<div class="form-row">
				<div class="col">
					<input type="text" class="form-control" name="first_name" value='<%=request.getAttribute("first_name")%>'/>
				</div>
				<div class="col">
					<input type="text" class="form-control" name="last_name"  value='<%=request.getAttribute("last_name")%>'/>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<label for="formGroupExampleInput">Geburtstag</label>
			<input type="date" class="form-control" name="birth_date" value='<%=request.getAttribute("birth")%>'/>
		</div>

		<div class="form-group">
			<div class="form-check form-check-inline">
				<input type="radio" name="gender" value="male"  <%= request.getAttribute("gender").equals("male") ?  "checked" : "" %>>
				<label class="form-check-label" for="inlineRadio1">Männlich</label>
			</div>
			<div class="form-check form-check-inline">
				<input type="radio" name="gender" value="female"  <%= request.getAttribute("gender").equals("female") ?  "checked" : "" %>>
				<label class="form-check-label" for="inlineRadio2">Weiblich</label>
			</div>
			<div class="form-check form-check-inline">
				<input type="radio" name="gender" value="other"  <%= request.getAttribute("gender").equals("other") ?  "checked" : "" %>>
				<label class="form-check-label" for="inlineRadio3">Neutral</label>
			</div>	
		</div>
		
		<div class="form-group">
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="inputEmail4">Email</label>
					<input type="email" class="form-control" name="email_address" value='<%=request.getAttribute("mail")%>'>
				</div>
				<div class="form-group col-md-6">
					<label for="inputPassword4">Telefon</label>
					<input type="tel" class="form-control" name="phone_number" value='<%=request.getAttribute("phone")%>'>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<label for="inputAddress">Addresse</label>
			<input type="text" class="form-control" name="address_line" value='<%=request.getAttribute("address")%>'>		
		</div>
		<div class="form-group">
			<input type="text" class="form-control" name="address_add" value='<%=request.getAttribute("address_add")%>' placeholder="Addresszeile 2">
		</div>
		
		<div class="form-group">
			<div class="form-row">
				<div class="col">
					<input type="text" class="form-control" name="post_code" value='<%=request.getAttribute("post_code")%>'>
				</div>
				<div class="col-7">
					<input type="text" class="form-control" name="city" value='<%=request.getAttribute("city")%>'>
				</div>
			</div>
		</div>

		<div class="form-group">
			<small id="passwordHelpBlock" class="form-text text-muted">
	  			Um ein neues Passwort zu generieren Checkbox benutzen. Ansonsten wird das alte Passwort beibehalten!
			</small>
			
			<div class="custom-control custom-checkbox">
  				<input type="checkbox" class="custom-control-input" id="customCheck1" name="password_change" value='<%=request.getAttribute("password")%>'>
  				<label class="custom-control-label" for="customCheck1"><%=request.getAttribute("password")%></label>
			</div>
		</div>
		<input id="submit_btn" class="btn btn-primary" type="submit" value="Änderungen speichern">
		<a class="btn btn-primary" href="overviewMember.jsp" role="button">Abbrechen</a>
		</form>
	</fieldset>
	
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</body>
</html>
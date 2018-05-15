<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	        <a class="nav-link" href="overviewMember.jsp">
	        	Mitglieder 
	        	<span class="sr-only">(current)</span>
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
		<!--  Formular needed -->
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	 %>

<h2>Mitgliederverwaltung</h2>

<div class="content-wrap">
	<!-- Aktuelle Mitglieder anzeigen -->
		<div class="mitlgieder_anzeigen">
			<h3>Aktuelle Mitglieder</h3>
		</div>
	<!-- Mitglied hinzufügen -->
		<div class="mitglied_hinzufügen">
			<h3>Mitglied hinzufügen</h3>	
			<form action="${pageContext.request.contextPath}/registerMember" method="post">
				<input type="text" name="first_name" placeholder="Nachname" /><br>
				<input type="text" name="last_name" placeholder="Vorname" /><br>
				<p>Geburtstag: (Format dd.MM.yyyy) <br>
					<input type="date" name="birth_date" placeholder="Geburtstag" /><br>
				</p>
				<input type="radio" name="gender" value="male"> Männlich<br>
	  			<input type="radio" name="gender" value="female"> Weiblich<br>
	  			<input type="radio" name="gender" value="other"> Neutral <br>
				<input type="email" name="email_address" placeholder="E-Mail" /><br>
				<input type="tel" name="phone_number" placeholder="Telefonnummer" /><br>
				<p>
					Adresse: <br>
					<input type="text" name="address_line" placeholder="Adresszeile 1" /><br>
					<input type="text" name="address_add" placeholder="Adresszeile 2" /><br>
					<input type="text" name="post_code" placeholder="Postleitzahl" /><input type="text" name="city" placeholder="Ort" /><br>
				</p>
				<p>
					Beigetreten am Format(Format dd.MM.yyyy)<br>
					<input type="date" name="entry_date" placeholder="Beigetreten am" /><br>
				</p>
				
				<p>

				<input type="submit" name="submit_mitglied" value="Mitglied hinzufügen"><br>


				<!-- Hier fehlt noch die Möglichkeit, den Mitgliedsbeitrag und evtl. die SEPA Einzugsermächtigung als PDF hochzuladen -->
			</form>
		</div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</body>
</html>
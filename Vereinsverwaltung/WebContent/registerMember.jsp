<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mitgliederverwaltung</title>
</head>
<body>
		<!--  Formular needed -->
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	 %>	
		
		<h2>Mitgliederverwaltung</h2>


	<!-- Aktuelle Mitglieder anzeigen -->
		<div class="mitlgieder_anzeigen">
			<h3>Aktuelle Mitglieder</h3>
		</div>
	<!-- Aktuelle Mitglieder anzeigen Ende -->


	<!-- Mitglied hinzufügen -->
		<div class="mitglied_hinzufügen">
			<h3>Mitglied hinzuf&uuml;gen</h3>	
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

				<input type="submit" name="submit_mitglied" value="Mitglied hinzuf&uuml;gen"><br>


				<!-- Hier fehlt noch die Möglichkeit, den Mitgliedsbeitrag und evtl. die SEPA Einzugsermächtigung als PDF hochzuladen -->
			</form>
		</div>
	<!-- Mitglied hinzufügen Ende -->
		
</body>
</html>
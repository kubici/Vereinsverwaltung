<%@page import="com.sw.servlets.MemberDashboardServlet"%>
<%@page import="com.sw.beans.Member" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="de">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/layout.css">
<link rel="stylesheet" href="css/fonts.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<% 
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");	
MemberDashboardServlet memberServlet = new MemberDashboardServlet();
pageContext.setAttribute("mList", memberServlet.getLstMember());
%>
  <title>Mitgliederübersicht</title>

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
		<li class="nav-item disabled">
	        <a class="nav-link btn btn-light text-left pl-2">
	        	Mitglieder 
	        	<span class="sr-only">(current)</span>
			</a>
	    </li>
   		<li class="nav-item active">
			<a class="nav-link btn btn-light text-left pl-2" href="overviewRoles.jsp">
				Rollen 
			</a>
	    </li>
   		<li class="nav-item active">
			<a class="nav-link btn btn-light text-left pl-2" href="overviewInventory.jsp">
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
</head>
<body>
<h1>Mitgliederübersicht</h1>

<div id="users-contain" class="content-wrap ui-widget">

<!-- TABELLE Mitglieder -->
<div class="table-responsive-lg">
	<table class="table table-hover">
	  	<thead>
	  		<tr>
	  			<th scope="col">Bild</th>
				<th scope="col">Vorname</th>
				<th scope="col">Nachname</th>
				<th scope="col">Geburtstag</th>
				<th scope="col">E-Mail </th>
				<th scope="col">Mitglied bearbeiten</th>
				<th scope="col">Mitglied löschen</th>
			</tr>
		</thead>
		</tbody>
			<c:forEach items="${mList}" var="mList" varStatus="loop">
				<tr>
					<td><img src="#"/></td>
					<td><c:out value="${mList.firstName}"></c:out></td>
					<td><c:out value="${mList.lastName}"></c:out></td>
					<td>!!!!!!</td>
					<td class=""><c:out value="${mList.emailAddress}"></c:out></td>
					<td>
						<!-- This form is needed to get the selected item -->
						<form action="editMember" method="post">
				   				<button class="btn btn-light" type="submit" name="id" value="${mList.memberId}" style="background-color:transparent; border-color:transparent;">
				   					<img src="./image/edit_icon.png" alt="Hallo" style="width:32px;height=32px; border=0"/>
				   				</button>
						</form>
					</td>
					<td>
						<form action="deleteMember" method="post"  onsubmit="return buttonPressed();">
				   				<button class="btn btn-light" id="deleteButton" type="submit" name="id" value="${mList.memberId}" style="background-color:transparent; border-color:transparent;">
				   					<img src="./image/delete_icon.png" style="width:32px;height=32px; border=0"/>
				   				</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
	<button type="button" class="btn btn-primary btn-lg btn-block" id="create-user">+</button>
</div>

<!-- DIALOG Mitglied hinzufügen  -->
<div id="dialog-form" title="Create new user">
  <p class="validateTips">All form fields are required.</p>
    <form>
    <fieldset>
				<input type="text" id="first_name" name="first_name" placeholder="Nachname" /><br>
				<input type="text" id="last_name" name="last_name" placeholder="Vorname" /><br>
				<p>Geburtstag: (Format dd.MM.yyyy) <br>
					<input type="date" id="birth_date" name="birth_date" placeholder="Geburtstag" /><br>
				</p>
				<input type="radio" class="radioBtnClass" id="gender" name="gender" value="male"> Männlich<br>
	  			<input type="radio" class="radioBtnClass" id="gender" name="gender" value="female"> Weiblich<br>
	  			<input type="radio" class="radioBtnClass" id="gender" name="gender" value="other"> Neutral <br>
				<input type="email" id="email_address" name="email_address" placeholder="E-Mail" /><br>
				<input type="tel" id="phone_number" name="phone_number" placeholder="Telefonnummer" /><br>
				<p>
					Adresse: <br>
					<input type="text" id="address_line" name="address_line" placeholder="Adresszeile 1" /><br>
					<input type="text" id="address_add" placeholder="Adresszeile 2" /><br>
					<input type="text" id="post_code" name="post_code" placeholder="Postleitzahl" />
					<input type="text" id="city" name="city" placeholder="Ort" /><br>
				</p>
				<p>
					Beigetreten am Format(Format dd.MM.yyyy)<br>
					<input type="date" id="entry_date" name="entry_date" placeholder="Beigetreten am" /><br>
				</p>
				
      <!-- Allow form submission with keyboard without duplicating the dialog button -->
      <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
    </fieldset>
  </form>
</div>

<!-- Controll delete Button -->
<script type="text/javascript">
	function buttonPressed()
	{		
		answer = confirm("Mitglied löschen?");
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
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>	
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="js/registerMember.js"></script>
</body>
</html>
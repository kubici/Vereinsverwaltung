<%@page import="com.sw.servlets.MemberDashboardServlet"%>
<%@page import="com.sw.beans.Member" %>
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
MemberDashboardServlet memberServlet = new MemberDashboardServlet();
pageContext.setAttribute("mList", memberServlet.getLstMember());
%>
<title>Mitgliederverwaltung</title>
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
  
	<h2>Mitgliederübersicht</h2>
	
	 <!-- Here you can see a table of all mitglieder in your team -->
	<jsp:useBean id="mitgliederList" class="com.sw.servlets.MemberDashboardServlet"></jsp:useBean>

<div class="content-wrap">
	<table border="3">
		<th>Vorname</th>
		<th>Nachname</th>
		<th>Mitglied bearbeiten</th>
		<th>Mitglied löschen</th>
		<c:forEach items="${mList}" var="mList" varStatus="loop">
		<tr>
			<td><c:out value="${mList.firstName}"></c:out></td>
			<td><c:out value="${mList.lastName}"></c:out></td>
			<td>
				<!-- This form is needed to get the selected item -->
				<form action="editMember" method="post">
		   				<button class="button" type="submit" name="id" value="${mList.memberId}" style="background-color:transparent; border-color:transparent;">
		   					<img src="./image/edit_icon.png" alt="Hallo" style="width:32px;height=32px; border=0"/>
		   				</button>
				</form>
			</td>
			<td>
				<form action="deleteMember" method="post"  onsubmit="return buttonPressed();">
		   				<button class="button" id="deleteButton" type="submit" name="id" value="${mList.memberId}" style="background-color:transparent; border-color:transparent;">
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
	
	 <!-- Here is the part for register new Mitglieder -->
	<form action="registerMember.jsp">		
		<br/>
		<br/>
		Neues Mitglied hinzufügen
		<input type = "submit" value = "+"/>
	</form>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>	
</body>
</html>
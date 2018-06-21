<!-- @ kubi + tobi -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@page import="com.sw.beans.Member"  %>
   <%@page import="com.sw.dao.MemberDao" %>
   <%@page import="com.sw.security.ParseDate" %>
    <%@page import="com.sw.filters.KeyFigures"  %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
   <%@page import="java.util.*" %>
	<%@page import="com.google.gson.*" %>
   
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/index.css">
<link rel="stylesheet" href="css/module.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js" integrity="sha256-JG6hsuMjFnQ2spWq0UiaDRJBaarzhFbUxiUTxQDA9Lk=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.min.js" integrity="sha256-XF29CBwU1MWLaGEnsELogU6Y6rcc5nCkhhx89nFMIDQ=" crossorigin="anonymous"></script>
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
	
<!--  TODO  -->

<% // Make a Servlet with provided the data needed for the charts. %>
<% // Anteil von Männern, Frauen, Neutral aus Datenbank lesen %>
<% KeyFigures keyFigures = new KeyFigures();
	String femalePercentage = "" + keyFigures.countFemalePercentage();
	String neutralPercentage = "" + keyFigures.countNeutralPercentage();
	String malePercentage = "" + keyFigures.countMenPercentage();
	
	// Data for Line chart
	String data2014 = "" + keyFigures.dataByDate(2014);
	String data2015 = "" + keyFigures.dataByDate(2015);
	String data2016 = "" + keyFigures.dataByDate(2016);
	String data2017 = "" + keyFigures.dataByDate(2017);
	String data2018 = "" + keyFigures.dataByDate(2018);
%>
	<!-- Test TK -->
</head>
<body>
<header>
<h1>Herzlich Willkommen!</h1>
</header>
<div id="particles-js"></div> 
<div class="content-wrap">
	<div class="container">
		<div class="row justify-content-center">
			<h2 style="	font-family: helvetica neue; font-weight: light; font-variant-caps: all-small-caps;">Statistik</h2>
		</div>
		<div class="row border p-3">
			<div class="col">
				<h5 class="text-center mb-3">neue Mitglieder seit 2014</h5>
				<canvas id="lineChart" height="300"></canvas>
			</div>
			
			
			<div class="col" id="chart">
				<h5 class="text-center mb-3">Geschlechter-Anteil</h5>
				<canvas id="donutChart" height="300"></canvas>
			</div>
			<% pageContext.setAttribute("mList", keyFigures.getNextFewBirthdays(3));  %>
			
			<div class="col">
				<h5 class="text-center mb-3">Nächste Geburtstage</h5>
				<table class="table table-hover">
				  <thead>
					 <tr>
						<th scope="col">Vorname</th>
						<th scope="col">Nachname</th>
						<th scope="col">Geburtstag</th>
					 </tr>
				  </thead>
				  <tbody>
				  <c:forEach items="${mList}" var="mList" varStatus="loop">
				  <tr>
				  	<td><c:out value="${mList.firstName}"></c:out></td>
				  	<td><c:out value="${mList.lastName}"></c:out></td>
				  	<%ParseDate parser = new ParseDate();
				  		Date birthdate = ((Member) pageContext.getAttribute("mList")).getBirth(); %>
				  	<td><%= parser.convertString(birthdate) %></td>
				  </c:forEach>
				  </tbody>
				</table>
			</div>
		</div>
		<div class="row mt-2 p-3">
			<div class="col-sm">
				<a class="module" href="./MemberServlet">
					<div id="card_img1"></div>
					<div id="card_content">
					  <h2>Mitglieder</h2>
				  </div>  
				</a>
			</div>
			<div class="col-sm">
				<a class="module" href="./RoleServlet">
					<div id="card_img2"></div>
					<div id="card_content">
						<h2>Rollen</h2>  
			  		</div>  
				</a>
			</div>
			<div class="col-sm">
				<a class="module" href="./InventoryServlet">
					<div id="card_img3"></div>
					<div id="card_content">
					  <h2>Inventar</h2>
				  </div>  
				</a>     
			</div>	
			<div class="col-sm">
				<a class="module" href="./TaskServlet">
					<div id="card_img3"></div>
					<div id="card_content">
					  <h2>Tasks</h2>
				  </div>  
				</a>     
			</div>	
		</div>
	</div>
</div>
<!-- Textarea to store data for doughnut chart -->
<textarea style="visibility: hidden;" id="femaleData" disabled="disabled"><%=femalePercentage%></textarea>
<textarea style="visibility: hidden;" id="neutralData" disabled="disabled"><%=neutralPercentage%></textarea>
<textarea style="visibility: hidden;" id="maleData" disabled="disabled"><%=malePercentage%></textarea>
<!-- Textarea to store data for line chart -->
<textarea style="visibility: hidden;" id="2014Data" disabled="disabled"><%=data2014%></textarea>
<textarea style="visibility: hidden;" id="2015Data" disabled="disabled"><%=data2015%></textarea>
<textarea style="visibility: hidden;" id="2016Data" disabled="disabled"><%=data2016%></textarea>
<textarea style="visibility: hidden;" id="2017Data" disabled="disabled"><%=data2017%></textarea>
<textarea style="visibility: hidden;" id="2018Data" disabled="disabled"><%=data2018%></textarea>
<script src="http://cdn.jsdelivr.net/particles.js/2.0.0/particles.min.js"></script> 
<script src="http://threejs.org/examples/js/libs/stats.min.js"></script>
<script src="js/particles.js"></script>
<script src="js/statistics.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.js" integrity="sha256-JG6hsuMjFnQ2spWq0UiaDRJBaarzhFbUxiUTxQDA9Lk=" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.2/Chart.bundle.min.js" integrity="sha256-XF29CBwU1MWLaGEnsELogU6Y6rcc5nCkhhx89nFMIDQ=" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
<script src="js/module.js"></script>
</body>
</html>
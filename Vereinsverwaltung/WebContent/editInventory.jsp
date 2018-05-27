<%@page import="com.sw.security.ParseDate" %>
<%@page import="java.util.Date" %>
<%@page import="com.sw.dao.InventoryDao"%>
<%@page import="com.sw.beans.Inventory" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/layout.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

<% 
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");	
InventoryDao idao = new InventoryDao();
pageContext.setAttribute("iList", idao.getInventoryById(Integer.parseInt(request.getParameter("id"))));
%>

<title>Inventar bearbeiten</title>
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
			<!--NAVBAR-ITEM -->
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
	   		<li class="nav-item disabled">
				<a class="nav-link btn btn-light text-left pl-2">
					Inventar 
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
				<a class="dropdown-item" href="changePassword.jsp">
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
<h2>Inventar bearbeiten</h2>
</header>
<div class="content-wrap">
	<fieldset class="mb-5 border p-4">
	<form action="${pageContext.request.contextPath}/InventoryEditServletSave" method="post">
		<h3>Inventar-ID: ${iList.inventoryId}</h3>
		
<!-- 	Date Parser -->
		<%
		ParseDate parser = new ParseDate();
		Date last_audit = ((Inventory) pageContext.getAttribute("iList")).getLastAudit();
		Date next_audit = ((Inventory) pageContext.getAttribute("iList")).getNextAudit();
		Date acquisition_date = ((Inventory) pageContext.getAttribute("iList")).getAcquisitionDate();
		%>

		<input type="hidden" name="id" value="${iList.inventoryId}"/>
		<div class="form-group">
			<label for="formGroupExampleInput">Kategorie</label>
  				<input type="text" class="form-control" name="category" value="${iList.category}" placeholder="Kategorie eingeben">
		</div>
		<div class="form-group">
				<label for="formGroupExampleInput">Beschreibung</label>
   				<input type="text" class="form-control" name="description" value="${iList.description}" placeholder="Beschreibung eingeben">
		</div> 
		<div class="form-group">
			<div class="form-row">
				<div class="col">
					<label>Anschaffung</label>
				</div>
				<div class="col-6 pl-4">
					<label>Kaufdatum</label>
				</div>
			</div>
			<div class="form-row">
				<div class="col">
					<div class="form-group row">
  							<div class="col">
 									<input type="text" class="form-control form-control-lg" id="colFormLabelLg" name="purchaseValue" value="${iList.purchaseValue}" placeholder="00.00">
		    			</div>
		    			<img class="pr-3 pl-0 ml-0" src="image/euro_icon.png" height="50"></img>
				  </div>    
				</div>
				<div class="col-6 pl-4">
					<input type="date" class="form-control form-control-lg" name="acquisition_date" value='<%=parser.convertStringII(acquisition_date) %>' placeholder="DD.MM.YYYY" />
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="inputnextaudit">nächste Prüfung am</label>
			<input type="date" class="form-control form-control-lg" name="next_audit" value='<%=parser.convertStringII(next_audit) %>' placeholder="DD.MM.JJJJ" />
		</div>
		<div class="form-group">
			<div class="form-row">
				<div class="col">
					<div class="form-group">
						<label for="inputlastaudit">letzte Prüfung am</label>
						<input type="date" class="form-control" name="last_audit" value='<%=parser.convertStringII(last_audit) %>' placeholder="DD.MM.JJJJ" />
					</div>    
				</div>
				<div class="col">
					<div class="form-group">
						<label for="inputlastauditby">letzte Prüfung von</label>
						<input type="text" class="form-control" name="last_audit_by" value="${iList.lastauditby}" placeholder="Prüfer eingeben" />
					</div>    
				</div>
			</div>
		</div>
		<input id="inventory_submit" class="btn btn-primary" type="submit" value="Änderungen speichern">
		<a id="inventory_submit" class="btn btn-primary" href="./InventoryDashboardServlet" role="button">Abbrechen</a>
	</form>
	</fieldset>
</div>

<!-- Override Button-Colors -->
<style>
	.btn-primary,
	.btn-primary:hover,
	.btn-primary:active,
	.btn-primary:visited,
	.btn-primary:focus {
	    background-color: #ffbf00 !important;
	    border-color: #ffbf00 !important;
		}
	
	#inventory_submit{
		color:black !important;
	}
</style>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>		
</body>
</html>
<%@page import="com.sw.servlets.InventoryServlet"%>
<%@page import="com.sw.dao.InventoryDao"%>
<%@page import="com.sw.beans.Inventory" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.sw.security.ParseDate" %>
<%@page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/layout.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

<!-- Loading inventarList from Controller -->
<% 
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
InventoryDao invendao= new InventoryDao();
pageContext.setAttribute("iList", invendao.readInventory());
%>

<title>Inventar</title>
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
			<!--NAVBAR-ITEM -->
			<li class="nav-item active">
		        <a class="nav-link btn btn-light text-left pl-2" href="./MemberServlet">
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
	   		<li class="nav-item active">
				<a class="nav-link btn btn-light text-left pl-2" href="./TaskServlet">
					Aufgaben 
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
					<input class="btn btn-warning btn-block" type="submit" value="Passwort ändern"/></a>
				<a class="dropdown-item" href="#">
					<form action="${pageContext.request.contextPath}/Logout" method="post">
						<input class="btn btn-warning btn-block" type="submit" value="Logout"/>
					</form>
				</a>
			</div>
		</div>
	</div>
</nav>
</head>
<body>
<header>
<h1 style="color:#e6ac00 !important">Inventarübersicht</h1>
</header>

<!--List of Inventory -->
<jsp:useBean id="inventoryList" class="com.sw.dao.InventoryDao"></jsp:useBean>

<div class="content-wrap">
	<!-- Table Roles -->
	<div class="table-responsive-lg">
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">Kategorie</th>
					<th scope="col">Beschreibung</th>
					<th scope="col">Wert</th>
					<th scope="col">Kaufdatum</th>
					<th scope="col">letzte Prüfung</th>
					<th scope="col">nächste Prüfung</th>
					<th scope="col">letzte Prüfung von</th>
					<th scope="col">Inventar bearbeiten</th>
					<th scope="col">Inventar löschen</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${iList}" var="iList" varStatus="loop">
					<tr>
							<% ParseDate parser = new ParseDate(); %>
							<td><c:out value="${iList.category}"></c:out></td>
							<td><c:out value="${iList.description}"></c:out></td>
							<td><c:out value="${iList.purchaseValue}"></c:out></td>
							<% Date ac_date = ((Inventory) pageContext.getAttribute("iList")).getAcquisitionDate(); %>
							<td><c:out value='<%=parser.convertString(ac_date) %>' ></c:out></td>
							<% Date last_audit = ((Inventory) pageContext.getAttribute("iList")).getLastAudit(); %>
							<td><c:out value='<%=parser.convertString(last_audit) %>' ></c:out></td>
							<% Date next_audit = ((Inventory) pageContext.getAttribute("iList")).getNextAudit(); %>
							<td><c:out value='<%=parser.convertString(next_audit) %>' ></c:out></td>
							<td><c:out value="${iList.lastauditby}"></c:out></td>
						<td>
							<!-- This form is needed to get the selected item -->
						 	<form action="editInventory.jsp" method="post">
					   				<button class="button" type="submit" name="id" value="${iList.inventoryId}" style="background-color:transparent; border-color:transparent;">
					   					<img src="./image/edit_icon.png" alt="Hallo" style="width:32px;height=32px; border=0"/>
					   				</button>
							</form>
						</td>
						<td>
							<form action="deleteInventory" method="post" onsubmit="return deletebuttonPressed();">
			   					<button class="button" id="deleteButton" type="submit" name="id" value="${iList.inventoryId}" style="background-color:transparent; border-color:transparent;">
				   					<img src="./image/delete_icon.png" style="width:32px;height=32px; border=0"/>
				   				</button> 
							</form>  
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>


<jsp:useBean id="List" class="com.sw.dao.InventoryDao"></jsp:useBean>

<!--NEW INVENTORY FORM -->
		<div class="collapse" id="collapse_addInventory">
		<fieldset class="mb-5 border p-4">
			<h3>neues Inventar hinzufügen</h3><br/>
			<form action="${pageContext.request.contextPath}/registerInventory" method="post">
				<div class="form-group">
					<label for="formGroupExampleInput">Kategorie</label>
    				<input type="text" class="form-control" name="category" placeholder="Kategorie eingeben">
				</div>
				<div class="form-group">
					<label for="formGroupExampleInput">Beschreibung</label>
    				<input type="text" class="form-control" name="description" placeholder="Beschreibung eingeben">
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
   									<input type="text" class="form-control form-control-lg" id="colFormLabelLg" name="purchaseValue" placeholder="00.00">
				    			</div>
				    			<img class="pr-3 pl-0 ml-0" src="image/euro_icon.png" height="50"></img>
						  </div>    
						</div>
						<div class="col-6 pl-4">
							<input type="date" class="form-control form-control-lg" name="acquisition_date" placeholder="DD.MM.JJJJ" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label for="inputnextaudit">nächste Prüfung am</label>
					<input type="date" class="form-control form-control-lg" name="next_audit" placeholder="DD.MM.JJJJ" />
				</div>
				<div class="form-group">
					<div class="form-row">
						<div class="col">
							<div class="form-group">
								<label for="inputlastaudit">letzte Prüfung am</label>
								<input type="date" class="form-control" name="last_audit" placeholder="DD.MM.JJJJ" />
							</div>    
						</div>
						<div class="col">
							<div class="form-group">
								<label for="inputlastauditby">letzte Prüfung von</label>
								<input type="text" class="form-control" name="last_audit_by" placeholder="Prüfer eingeben" />
							</div>    
						</div>
					</div>
				</div>
				<button type="submit" class="btn btn-warning" name="submit_inventory">
					<a style="color:black">Inventar anlegen</a>
				</button>
				<button type="reset" class="btn btn-warning" data-toggle="collapse" href="#collapse_addInventory" role="button" aria-expanded="false" aria-controls="collapse_addInventory">
					<a style="color:black">Abbrechen</a>
				</button>		
			</form>
		</fieldset>
		</div>
		<button type="button" id="collapse-button" class="btn btn-warning btn-lg btn-block" data-toggle="collapse" href="#collapse_addInventory" role="button" aria-expanded="false" aria-controls="collapse_registerMember">
			<img src="./image/add_icon_black.png" height="25"></img>
		</button>
	</div>
</div>	

<style>
#collapse-button{
border-radius: 15px;
}
</style>

<!-- Controll delete Button -->
<script type="text/javascript">
	function deletebuttonPressed()
	{		
		answer = confirm("Inventar löschen?");
		if(answer == true)
		{
	   			return true;
		}
		else if(answer == false)
		{
			return false;
		}
	}
</script>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</body>
</html>
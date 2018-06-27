<%@page import="com.sw.dao.MemberHasRoleDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.sw.dao.MemberDao"%>
<%@page import="com.sw.beans.Member" %>
<%@page import="com.sw.beans.Role" %>
<%@page import="com.sw.dao.RoleDao" %>
<%@page import="com.sw.security.ParseDate" %>
<%@page import="com.sw.security.Generator" %>
<%@page import="java.util.Date" %>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/layout.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

<% 
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");	
MemberDao memberdao = new MemberDao();
pageContext.setAttribute("mList", memberdao.getMemberByUsername(request.getParameter("id")));
RoleDao roledao = new RoleDao();
pageContext.setAttribute("rList", roledao.getRoles());
MemberHasRoleDao member_has_role_dao = new MemberHasRoleDao();
ArrayList<Integer> list = member_has_role_dao.getRoleIdByMemberId( ((Member)pageContext.getAttribute("mList")).getMemberId() );
try {

} catch (Exception e) {
	e.printStackTrace();
	}
%>

<title>Mitglieder</title>

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
	    	<a class="nav-link btn btn-light text-left pl-2" href="./MemberServlet">
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
	    	<li class="nav-item active">
			<a class="nav-link btn btn-light text-left pl-2" href="./TaskServlet">
				Aufgaben 
			</a>
		</li>	
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

<h2>Mitglied bearbeiten</h2>
	
<div class="content-wrap">
	<fieldset class="mb-5 border p-4">
		<form action="${pageContext.request.contextPath}/saveMember" method="post">
		
		<h4>Benutzer: <c:out value="${mList.username}"></c:out></h4><br/>
		<input type="hidden" value="${mList.username}" name="username" />
		
		<div class="form-group">
			<div class="form-row">
				<div class="col">
					<input type="text" class="form-control" name="first_name" value="${mList.firstName}"/>
				</div>
				<div class="col">
					<input type="text" class="form-control" name="last_name"  value="${mList.lastName}"/>
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<label for="formGroupExampleInput">Geburtstag</label>
			<% 	ParseDate parser = new ParseDate();
				Date birthdate = ((Member) pageContext.getAttribute("mList")).getBirth();
			%>
			<input type="date" class="form-control" name="birth_date" value='<%=parser.convertStringII(birthdate) %>'/>
		</div>

		<div class="form-group">
			<div class="form-check form-check-inline">
				<input type="radio" name="gender" value="male"  <%= ((Member) pageContext.getAttribute("mList")).getGender().equals("male") ?  "checked" : "" %>>
				<label class="form-check-label" for="inlineRadio1">Männlich</label>
			</div>
			<div class="form-check form-check-inline">
				<input type="radio" name="gender" value="female"  <%= ((Member) pageContext.getAttribute("mList")).getGender().equals("female") ?  "checked" : "" %>>
				<label class="form-check-label" for="inlineRadio2">Weiblich</label>
			</div>
			<div class="form-check form-check-inline">
				<input type="radio" name="gender" value="other"  <%= ((Member) pageContext.getAttribute("mList")).getGender().equals("other") ?  "checked" : "" %>>
				<label class="form-check-label" for="inlineRadio3">Neutral</label>
			</div>	
		</div>
		
		<div class="form-group">
			<div class="form-row">
				<div class="form-group col-md-6">
					<label for="inputEmail4">Email</label>
					<input type="email" class="form-control" name="email_address" value="${mList.emailAddress}">
				</div>
				<div class="form-group col-md-6">
					<label for="inputPassword4">Telefon</label>
					<input type="tel" class="form-control" name="phone_number" value="${mList.phoneNumber}">
				</div>
			</div>
		</div>
		
		<div class="form-group">
			<label for="inputAddress">Addresse</label>
			<input type="text" class="form-control" name="address_line" value="${mList.adressline}">		
		</div>
		<div class="form-group">
			<input type="text" class="form-control" name="address_add" value="${mList.adresslineAdd}" placeholder="Addresszeile 2">
		</div>
		
		<div class="form-group">
			<div class="form-row">
				<div class="col">
					<input type="text" class="form-control" name="post_code" value="${mList.postCode}">
				</div>
				<div class="col-7">
					<input type="text" class="form-control" name="city" value="${mList.city}">
				</div>
			</div>
		</div>

		
		<div class="form-group">
			<label for="formGroupExampleInput">Rolle</label>		
			<c:forEach items="${rList}" var="rList" varStatus="loop">
				<div class="form-check">
				<input class="form-check-input" id="defaultCheck1" type="checkbox" name="member_has_role" value="${rList.role_id}"
				 <%=list.contains( ((Role) pageContext.getAttribute("rList")).getRole_id()) ? "checked" : "" %> />
				<label class="form-check-label" for="defaultCheck1">
					<c:out value="${rList.role_description}"></c:out> 
				</label>
				</div>
			</c:forEach>
		</div>
		
		
		<div class="form-group">
			<small id="passwordHelpBlock" class="form-text text-muted">
	  			Um ein neues Passwort zu generieren Checkbox benutzen. Ansonsten wird das alte Passwort beibehalten!
			</small>
			
			<div class="custom-control custom-checkbox">
			<%
			Generator generator = new Generator();
			String newPassword = generator.generatePassword();
			%>
  				<input type="checkbox" class="custom-control-input" id="customCheck1" name="password_change" value='<%=newPassword%>'>
  				<label class="custom-control-label" for="customCheck1"><%=newPassword%></label>
			</div>
		</div>
		<input id="submit_btn" class="btn btn-primary" type="submit" value="Änderungen speichern">
		<a class="btn btn-primary" href="./MemberServlet" role="button">Abbrechen</a>
		</form>
	</fieldset>
</div>

<!-- Override Button-Colors -->
<style>
	.btn-primary,
	.btn-primary:active,
	.btn-primary:visited,
	.btn-primary:focus
	{
		 background-color: #6eb9f7 !important;
		 border-color: #6eb9f7 !important;
	}
	.btn-primary:hover{
		background-color: #2196F3 !important;
		border-color: #2196F3 !important;
</style>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
</body>
</html>
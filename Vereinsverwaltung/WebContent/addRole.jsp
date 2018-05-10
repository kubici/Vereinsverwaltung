<%@page import="com.sw.dao.ModuleDao"%>
<%@page import="com.sw.beans.Module" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/layout.css">
<link rel="stylesheet" href="css/fonts.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

<% 
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");	
ModuleDao moduledao = new ModuleDao();
pageContext.setAttribute("mList", moduledao.getModules());
%>
<title>Rolle hinzufügen</title>
</head>
<body>

	<jsp:useBean id="roleList" class="com.sw.dao.ModuleDao"></jsp:useBean>
	
	<h3>Rolle hinzuf&uuml;gen</h3>	
	<jsp:useBean id="moduleList" class="com.sw.dao.ModuleDao"></jsp:useBean>
	
	<form action="${pageContext.request.contextPath}/addRole" method="post">
		<input type="text" name="role_description" placeholder="Beschreibung" /><br>
		<h6>Hier Zugriffs... Kubi denk dir bitte an gscheiten Text aus :P</h6>
		<c:forEach items="${mList}" var="mList" varStatus="loop">
			<input type="checkbox" name="role_module_access" value="${mList.module_id}"  /> 
			<c:out value="${mList.module_description}"></c:out> 
			<br>
		</c:forEach>
		<input type="submit" name="submit_role" value="Rolle hinzuf&uuml;gen"><br>
	</form>
</body>
</html>
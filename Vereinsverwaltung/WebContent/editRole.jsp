<%@page import="com.sw.dao.ModuleDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.sw.dao.RoleModuleAccessDao"%>
<%@page import="com.sw.beans.Module" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<% 
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");	
ModuleDao moduledao = new ModuleDao();
pageContext.setAttribute("mList", moduledao.getModules());
RoleModuleAccessDao accessdao = new RoleModuleAccessDao();
ArrayList<Integer> list = accessdao.getModuleIdByRoleId(Integer.parseInt(request.getParameter("role_id")));
%>

<title>Rolle bearbeiten</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/editRole" method="post">
		<input type="hidden" name="role_id" value=<%=request.getParameter("role_id") %> />
		<input type="text" name="role_description" value=<%=request.getParameter("role_description") %> /><br>
		
		<c:forEach items="${mList}" var="mList" varStatus="loop">
			<input type="checkbox" name="role_module_access" value="${mList.module_id}"
			 <%=list.contains( ((Module) pageContext.getAttribute("mList")).getModule_id()) ? "checked" : "" %> />  <!-- Fick dich, du scheiß Zeile Code! -->
			<c:out value="${mList.module_description}"></c:out> 
			<br>
		</c:forEach>
		<input type="submit" name="submit_role" value="Bearbeitung abschließen"><br>
	</form>
</body>
</html>

<%@page import="com.sw.dao.InventoryDao"%>
<%@page import="com.sw.beans.Inventory" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<% 
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");	

%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Inventar bearbeiten</title>
</head>
<body>
<h2>Inventar bearbeiten</h2>
	
	<form action="${pageContext.request.contextPath}/InventoryEditServletSave" method="post">

		<input type="hidden" name="inventory_id" value=<%=request.getParameter("inventory_id") %> />
			<input type="text" name="category"  value='<%=request.getAttribute("category")%>'/><br>
			<input type="text" name="description"  value='<%=request.getAttribute("description")%>'/><br>	
			<input type="text" name="purchase_value"  value='<%=request.getAttribute("purchase_value")%>'/><br>
		<p><br>
			<input type="date" name="last_audit" value='<%=request.getAttribute("last_audit")%>'/><br>
			<input type="date" name="next_audit" value='<%=request.getAttribute("next_audit")%>'/><br>
			<input type="date" name="acquisition_date" value='<%=request.getAttribute("acquisition_date")%>'/><br>
			<input type="date" name="last_audit_by" value='<%=request.getAttribute("last_audit_by")%>'/><br>
		</p>
				

		<input type="submit" name="submit_inventar" value="inventar Bearbeiten"><br>				
				

</form>
		
</body>
</html>
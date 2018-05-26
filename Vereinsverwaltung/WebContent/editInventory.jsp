<%@page import="com.sw.security.ParseDate" %>
<%@page import="java.util.Date" %>
<%@page import="com.sw.dao.InventoryDao"%>
<%@page import="com.sw.beans.Inventory" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<% 
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");	
InventoryDao idao = new InventoryDao();
pageContext.setAttribute("iList", idao.getInventoryById(Integer.parseInt(request.getParameter("id"))));
%>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Inventar bearbeiten</title>
</head>
<body>
<h2>Inventar bearbeiten</h2>
	
	<form action="${pageContext.request.contextPath}/InventoryEditServlet" method="post">

		<input type="hidden" name="id" value="${iList.inventoryId}" />
			<input type="text" name="category"  value="${iList.category}"/><br>
			<input type="text" name="description"  value="${iList.description}"/><br>	
			<input type="text" name="purchase_value"  value="${iList.purchaseValue}"/><br>
		<p><br>
			<%
			ParseDate parser = new ParseDate();
			Date last_audit = ((Inventory) pageContext.getAttribute("iList")).getLastAudit();
			Date next_audit = ((Inventory) pageContext.getAttribute("iList")).getNextAudit();
			Date acquisition_date = ((Inventory) pageContext.getAttribute("iList")).getAcquisitionDate();
			%>
			<input type="date" name="last_audit" value='<%=parser.convertStringII(last_audit) %>' /><br>
			<input type="date" name="next_audit" value='<%=parser.convertStringII(next_audit) %>' /><br>
			<input type="date" name="acquisition_date" value='<%=parser.convertStringII(acquisition_date) %>' /><br>
			<input type="text" name="last_audit_by" value="${iList.lastauditby}"/><br>
		</p>
				

		<input type="submit" name="submit_inventar" value="inventar Bearbeiten"><br>				
				

</form>
		
</body>
</html>
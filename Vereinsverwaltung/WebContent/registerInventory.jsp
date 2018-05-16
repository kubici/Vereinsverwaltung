<%@page import="com.sw.dao.InventoryDao"%>
<%@page import="com.sw.beans.Inventory" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<% 
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");	
InventoryDao invendao = new InventoryDao();
pageContext.setAttribute("mList", invendao.readInventory());
%>

<title>Inventar Hinzufügen</title>
</head>

<jsp:useBean id="List" class="com.sw.dao.InventoryDao"></jsp:useBean>
	
	<h3>Inventar hinzufügen</h3>	
	
	
<body>
<form action="${pageContext.request.contextPath}/registerInventory" method="post">

	
	<input type="text" name="category" placeholder="Category" /><br>
				<input type="text" name="description" placeholder="Description" /><br>
				 <br>
					<input type="text" name="purchase_value" placeholder="purchaseValue" /><br>
				
				<input type="date" name="last_audit" placeholder="LastAudit"> <br>
				
	  			<input type="date" name="next_audit" placeholder="NextAudit"> <br>
	  			
				<input type="date" name="acquisition_date" placeholder="AcquisitionDate" /><br>
				
				<input type="date" name="last_audit_by" placeholder="lastAuditBy" /><br>

				<input type="submit" name="submit_inventory" value="Inventar hinzfügen"><br>

</form>

</body>
</html>
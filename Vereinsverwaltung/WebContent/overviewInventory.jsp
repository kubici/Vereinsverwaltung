<%@page import="com.sw.dao.InventoryDao"%>
<%@page import="com.sw.beans.Inventory" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<!-- Loading inventarList from Controller -->
<% 
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");	
InventoryDao invendao= new InventoryDao();
pageContext.setAttribute("iList", invendao.readInventory());
%>

<title>Inventar Verwaltung</title>

</head>
<body>

<h2>Inventarübersicht</h2>
	
	 <!-- Here you can see a table of all Inventar-->
	<jsp:useBean id="inventoryList" class="com.sw.dao.InventoryDao"></jsp:useBean>

<div id="users-contain" class="content-wrap ui-widget">
<table border="3">
		<th>Category</th>
		<th>Description</th>
		<th>PurchaseValue</th>
		<th>LastAudit</th>
		<th>NextAudit</th>
		<th>acquisitionDate</th>
		<th>LastAuditBy</th>
		<th>inventar bearbeiten</th>
		<th>Inventar löschen</th>
		
		<c:forEach items="${iList}" var="iList" varStatus="loop">
		<tr>
			<td><c:out value="${iList.category}"></c:out></td>
			<td><c:out value="${iList.description}"></c:out></td>
			<td><c:out value="${iList.purchaseValue}"></c:out></td>
			<td><c:out value="${iList.lastAudit}"></c:out></td>
			<td><c:out value="${iList.nextAudit}"></c:out></td>
			<td><c:out value="${iList.acquisitionDate}"></c:out></td>
			<td><c:out value="${iList.lastauditby}"></c:out></td>
	
			
			<td>
				<!-- This form is needed to get the selected item -->
			 	<form action="editInventory" method="post">
			 	
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
	</table>

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
				alert("Löschvorgang abgebrochen!");
				return false;
			}
		}
	</script>
</div>

<form action="registerInventory.jsp">		
		<br/>
		<br/>
		Neues Inventar hinzufügen
		<input type = "submit" value = "+"/>
	</form>


</body>
</html>
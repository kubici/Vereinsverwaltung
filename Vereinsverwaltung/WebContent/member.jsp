<%@page import="com.sw.controller.MemberDashboardController"%>
<%@page import="com.sw.beans.Member" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

 <!-- Loading MitgliederList from Controller -->
	<% 
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");	
	MemberDashboardController controller = new MemberDashboardController();
	pageContext.setAttribute("mList", controller.getLstMember());
	 %>
		 
<html>
<head>

	<title>Mitgliederverwaltung</title>
	<link href="css/memberButton.css" rel="stylesheet" type="text/css">

</head>
<body>
	<h2>Mitgliederübersicht</h2>
	
	 <!-- Here you can see a table of all mitglieder in your team -->
	<jsp:useBean id="mitgliederList" class="com.sw.controller.MemberDashboardController"></jsp:useBean>

	<table border="3">
	<th>Vorname</th>
	<th>Nachname</th>
	<th>Mitglied bearbeiten</th>
	<th>Mitglied löschen</th>
	<c:forEach items="${mList}" var="mList" varStatus="loop">
	<tr>
		<td><c:out value="${mList.firstName}"></c:out></td>
		<td><c:out value="${mList.lastName}"></c:out></td>
		<td>
			<!-- This form is needed to get the selected item -->
			<form action="editMember" method="post">
    				<button class="button" type="submit" name="id" value="${mList.memberId}" style="background-color:transparent; border-color:transparent;">
    					<img src="./image/edit_icon.png" alt="Hallo" style="width:32px;height=32px; border=0"/>
    				</button>
			</form>
		</td>
		<td>
			<form action="deleteMember" method="post"  onsubmit="return buttonPressed();">
    				<button class="button" id="deleteButton" type="submit" name="id" value="${mList.memberId}" style="background-color:transparent; border-color:transparent;">
    					<img src="./image/delete_icon.png" style="width:32px;height=32px; border=0"/>
   
    				</button>
			</form>
		</td>
	</tr>
	</c:forEach>
	</table>
	
	<!-- Controll delete Button -->
	<script type="text/javascript">
	function buttonPressed()
	{		
		answer = confirm("Mitglied löschen?");
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
	

	
	 <!-- Here is the part for register new Mitglieder -->
	<form action="registerMember.jsp">

	<br/>
	<br/>
	Neues Mitglied hinzufügen
	<input type = "submit" value = "+"/>
	</form>
	
</body>
</html>
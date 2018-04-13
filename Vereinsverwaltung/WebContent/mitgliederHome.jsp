<%@page import="com.sw.beans.Mitglieder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
		<p> This is the Overview of all Mitglieder </p>
		

		The student is confirmed: ${param.plus}
		
		<form action="mitgliederView.jsp">
		Add User <input type = "submit" value = "+"/>

		<br/>
	<br/>
	
	<!-- Statische version wird rausgenommen-->

<table border="3">
  
<tr>
<td> Id </td> 
<td> Name</td>
<td> lastname</td>
<td> birthdate</td>
<td> adressline01</td>
<td> adressline02</td>
<td> postalcode</td>
<td> city</td>
<td> gender</td>
<td> telefon</td>
<td> email</td>
<td> joinedDate</td>
<td> role</td>
  </tr>

<tr>
<td>Werte ID </td>
<td>werte Name </td>
<td>werte lName </td>
<td>Werte birthdate </td>
<td>null</td>
<td>null </td>
<td>null</td>
<td>null </td>
<td>null</td>
<td>null</td>
<td>null</td>
<td>null</td>
<td>null</td>


</tr>


</table>

<!-- Dynamische version wird noch angepasst -->

<script type="text/javascript">

	var table = '';
	var rows = 10;
	var cols = 5;
	var value = "§test $$";
	for(var r = 0; r< rows; r++){
		table += '<tr>';
		for( var c =0; c< cols; c++){
			table += '<td>'+value +'</td>';
		
		}
		
		table += '</tr>';
	}
	
	document.write('<table border = 3>' + table+ '</table>');
	
</script>

		
</body>
</html>
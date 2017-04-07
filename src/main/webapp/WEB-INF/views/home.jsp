<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SQL - MySQL</title>
<link rel="stylesheet" href="css/styles.css">
</head>
<body>
	<h3>Address Book App (Using MySQL)</h3>
	<a href="home?action=new">* New Contact</a>
	<hr>
	<table>
		<thead>
			<th>Name</th>
			<th>Email</th>
			<th>Mobile</th>
			<th></th>
			<th></th>
		</thead>
		<tbody>
			<c:forEach items="${contacts}" var="contact">
				<tr>
					<td><c:out value="${contact.name}"/></td>
					<td><c:out value="${contact.email}" /></td>
					<td><c:out value="${contact.mobile}" /></td>
					<td>
						<a href="home?action=edit&id=<c:out value="${contact._id}" />">Edit</a>
						<a href="home?action=delete&id=<c:out value="${contact._id}" />">Delete</a>
					</td>
					<td>
						<form action="home" method="POST">
						<input type="submit" value="Convert"></form>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
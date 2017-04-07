<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SQL - MySQL</title>
<link rel="stylesheet" href="css/styles.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<!-- Custom Fonts -->
    <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>
</head>
<body>
<div class="container">
	<h3><c:if test="${empty document}">New</c:if> Contact Form</h3>
	</div> 
	<form action="home" method="POST">
		<hr>

		<c:if test="${!empty document}">
			<input name="id" type="hidden" value="${document._id}">
		</c:if>
		<div class="container">
        <div class = "col-lg-6">
		<ul class="fields">
			
			<fieldset class="form-group">
			<li>Name: <input name="name" type="text" class="form-control" id="formGroupExampleInput"
				placeholder="Please enter your name." required
				<c:if test="${!empty document}">value="${document.name}"</c:if>>
			</li>
			</fieldset>
			<fieldset class="form-group">
			<li>Email: <input name="email" type="email" class="form-control" id="formGroupExampleInput"
				placeholder="Please enter your email." required
				<c:if test="${!empty document}">value="${document.email}"</c:if>>
			</li>
			<fieldset class="form-group">
			<li>Mobile No.: <input name="mobile" type="text" class="form-control" id="formGroupExampleInput"
				placeholder="Please enter your mobile no." required
				<c:if test="${!empty document}">value="${document.mobile}"</c:if>>
			</li>
			</fieldset>
		</ul>
		
		<button type="submit" class="btn btn-primary">Save</button>
		<a href="home">Cancel</a>
		
		</div>
    </div>

	</form>
</body>
</html>
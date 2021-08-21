<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login And Register</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="CSS/main.css"/>
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="wrapper">
	<div class="container">
	<h1>Register</h1>
	<p><form:errors path="user.*"/></p>
	    
	    <form:form method="POST" action="/newuser" modelAttribute="user">
	        <p>
	            <form:label path="firstName">First Name:</form:label>
	            <form:input type="firstName" path="firstName"/>
	        </p>
	        <p>
	            <form:label path="lastName">Last Name:</form:label>
	            <form:input type="lastName" path="lastName"/>
	        </p>
	        <p>
	            <form:label path="email">Email:</form:label>
	            <form:input type="email" path="email"/>
	        </p>
	        <p>
	            <form:label path="location">Location:</form:label>
	            <form:input type="location" path="location"/>
	            <form:select path="state">
		            <option value="CA">CA</option>
		            <option value="TX">TX</option>
		            <option value="NY">NY</option>
		            <option value="WA">WA</option>
	            </form:select>
	        </p>
	        <p>
	            <form:label path="password">Password:</form:label>
	            <form:password path="password"/>
	        </p>
	        <p>
	            <form:label path="passwordConfirmation">Password Confirmation:</form:label>
	            <form:password path="passwordConfirmation"/>
	        </p>
	        <input type="submit" value="Register"/>
	    </form:form>
	</div>
	<div class="container">
	<h1>Login</h1>
	<p>${error}</p>
	    
	    <form method="post" action="/login">
	        <p>
	            <label type="email" for="email">Email:</label>
	            <input type="email" name="email"/>
	        </p>
	        <p>
	            <label for="password">Password:</label>
	            <input type="password" name="password"/>
	        </p>
	        <button>Login</button>
	    </form>
	</div>
</div>
</body>
</html>
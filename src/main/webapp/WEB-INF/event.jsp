<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title><c:out value="${event.name}"/></title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/CSS/main.css"/>
<script src="/webjars/jquery/jquery.min.js"></script>
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<h2><c:out value="${event.name}"/></h2>
<div class="wrapper">
	<div class="container">
		<h3>Host:<c:out value="${event.host.firstName}"/></h3>
		<h3>Date:<fmt:formatDate pattern="MMMM-dd-yyy" value="${event.date}"/></h3> 
		<h3>Location:<c:out value="${event.location}"/>,<c:out value="${event.state}"/></h3>
		<h3>People who are attending this event:<c:out value="${event.eventUsers.size()+1}"/></h3>
	
		<table>
			<thead>
				<tr>
					<th>Name</th>
					<th>Location</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${event.eventUsers}" var="user">
				<tr>
						<td>${user.firstName} ${user.lastName}</td>
						<td>${user.location}</td>	
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div class="container">
		<h3>Message Wall</h3>
		<c:forEach items="${event.messages}" var="comment">
		<p>${comment.text}</p>
		<p>Posted By ${comment.userMessage.firstName}</p>
		
		</c:forEach>
	</div>
	</div>
	<div class="newcomment">
		<h3>Add Comment:</h3>
		<form:form method="POST" action="/event/${event.id}/newcomment" modelAttribute="message">
		 	<p>
		        <form:textarea path="text"/>
		    </p>
		        <form:hidden path="userMessage" value="${user.id}"/>
		        <form:hidden path="eventMessage" value="${event.id}"/>
		        <button>Submit</button>
		</form:form>
	</div>

<button><a href="/home">Go Back</a></button>
</body>
</html>
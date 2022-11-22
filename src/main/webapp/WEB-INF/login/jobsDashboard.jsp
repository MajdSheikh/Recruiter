<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Jobs Dashboard</title>
<link rel="stylesheet" type="text/css" href="/css/dashboard.css">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />


</head>
<body>

<nav class="navbar navbar-dark bg-dark">
  <a class="navbar-brand mar">FreeHire</a>
<div>
<a href="/" class="navbar-brand fs-6">About us</a>
<a href="/create"class="navbar-brand fs-6">Are you hiring?</a>
<a href="/register" class="navbar-brand fs-6">Are you looking for a job?</a>
<a href="/jobs/dashboard" class="navbar-brand fs-6">Available jobs</a>

</div>
</nav>





<div class="space">
<h3>List of available jobs</h3>
</div>
<div class="rightbutton">
<a href="projects/new" type="button" class="btn btn-outline-primary">Add a new job opening</a>
</div>

<div class="marg">
<table class="table table-striped container">
	            <thead>
	                <tr>
	                    <th scope="col">location</th>
	                    <th scope="col" >Description</th>
	                    <th scope="col" >Starting Date:</th>
	                    <th scope="col" >Finishing Date:</th>
	                    <th scope="col">Specialization</th>
	                    <th scope="col">ِAction</th>
	                </tr>
	            </thead>
	            <tbody>
	            	<c:forEach var="service" items="${allServices}">
	            	
		                <tr>
		                	<td scope="row"><c:out value="${service.location}"/></td>                	
		                    <td><c:out value="${service.description}"/></td>
		                    <td><c:out value="${service.startingDate}"/></td> 
		                    <td><c:out value="${service.finishingDate}"/></td> 
		                    <td><c:out value="${service.specialization}"/></td>
		                    <c:choose>
		                    <c:when test="${user_id == service.owner.id || service.owner.id==null }">
		                    <td><a href="/services/${service.id}/edit">edit your post</a></td>
		                    </c:when>
		                     <c:when test="${service.company==null }">
		                    <td><a href="services/${service.id}/apply">Apply</a></td>
		                    </c:when>
		                     <c:otherwise>
		                    
		               		<td><a href="services/${service.id}/unjoin">dismiss</a></td>
		                    
		                    </c:otherwise>
		                    </c:choose>
		                </tr>
		                
	                </c:forEach>
	            </tbody>
	        </table>
</div>

</body>
</html>



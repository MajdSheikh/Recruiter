<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link rel="stylesheet" type="text/css" href="/css/card.css">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>create company</title>
</head>
<body>


<nav class="navbar navbar-dark bg-dark">
  <a class="navbar-brand mar">FreeHire</a>
<div>
<a href="/" class="navbar-brand fs-6">About us</a>
<a href="/projects/new" class="navbar-brand fs-6">Are you hiring?</a>
<a href="/register" class="navbar-brand fs-6">Are you looking for a job?</a>
<a href="/jobs/dashboard" class="navbar-brand fs-6">Available jobs</a>
<a href="/create/company" class="navbar-brand fs-6">Create Company</a>

<a href="/logout" class="navbar-brand fs-6">logout</a>


</div>
</nav>

<a href="/home" type="button" class="btn btn-outline-primary">Back</a>


<table class="table table-striped container m-5 ">
	            <thead>
	                <tr>
	                	<th scope="col">Company</th>
	                    <th scope="col">Contractor</th>
	                    <th scope="col">ِAction</th>
	                </tr>
	            </thead>
	            <tbody>
	            	<c:forEach var="company" items="${companies}">
	            	
		                <tr>
		                    <td><c:out value="${company.title}"/></td> 
		                    <td><c:out value="${company.contractor.firstName}"/></td> 
		                  	<td><a href="/show/${company.id }">Edit</a></td> 
		                  	<td><a href="/delete/${company.id }">delete</a></td> 
		                  	
		                </tr>
		                
	                </c:forEach>
	            </tbody>
	        </table>

 <form:form action="/company" method="post" modelAttribute="company" class="container">
	    	
	        <div class="form-group col-md-6 break">
	            <form:label path="title"><strong>Company Name :</strong></form:label>
	            <form:errors path="title"/>
	            <form:input type="text" path="title" class="form-control"/>
	        </div>
	        
	        
	       

	        
	        <input  type="submit" value="Create" type="button" class="btn btn-warning btn-lg btn-block m-3">
	    </form:form>
</body>
</html>
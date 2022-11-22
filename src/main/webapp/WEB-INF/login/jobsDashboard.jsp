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
<a href="/projects/new" class="navbar-brand fs-6">Are you hiring?</a>
<a href="/team" class="navbar-brand fs-6">Are you looking for a job?</a>
<a href="/jobs/dashboard" class="navbar-brand fs-6">Available jobs</a>
<a href="/create/company" class="navbar-brand fs-6">Create Company</a>

<a href="/logout" class="navbar-brand fs-6">logout</a>


</div>
</nav>




<div class="space">
<h3>List of available jobs</h3>
</div>
<div class="rightbutton">
<a href="/home" type="button" class="btn btn-outline-primary">Back</a>

</div>

<div class="marg">
<table class="table table-striped container">
	            <thead>
	                <tr>
	                	<th scope="col">Name of Posted</th>
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
	            	                                                      
	            	       <c:if test="${service.status==true}">
	    
		                <tr>
     			      		<td scope="row"><a href="/owner/dash/${service.owner.id}"><c:out value="${service.owner.firstName}"/></a></td>                	
		                	<td scope="row"><c:out value="${service.location}"/></td>                	
		                    <td><c:out value="${service.description}"/></td>
		                    <td><c:out value="${service.startingDate}"/></td> 
		                    <td><c:out value="${service.finishingDate}"/></td> 
		                    <td><c:out value="${service.specialization}"/></td>
		                    <c:choose>
		                    <c:when test="${user.id == service.owner.id }">
		                    <td><a href="/services/${service.id}/edit">edit your post</a></td>
		                    </c:when>
		                    
		                     <c:when test="${service.company==null &&  companies.size()>0}">
		                     <td>
		                     <form action="/services/${service.id}/apply" method="post">
		                     <select name="company">
		                     <c:forEach var="company" items="${companies}">
		                    <option value="${company.id}"> <c:out value="${company.title }"></c:out></option>
		                    </c:forEach>
		                    </select>
		                    	        <input  type="submit" value="Submit" type="button" class="btn btn-primary btn-lg btn-block">
		                    
		                    </form>
		                    </td>
		                    </c:when>
		                     <c:when test="${service.company!=null }">
		                    
		               		<td> Already company reserved <a href="/services/${service.id}/unjoin">dismiss</a></td>
		                    
 							</c:when>
 		                    </c:choose>
		                </tr>
		                </c:if>
	                </c:forEach>
	            </tbody>
	        </table>
</div>

</body>
</html>



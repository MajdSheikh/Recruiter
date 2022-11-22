
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Owner Dashboard</title>
<link rel="stylesheet" type="text/css" href="/css/ownerdashboard.css">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />


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


<div class="h">
<h3>List of your posted jobs</h3>
<div class="rightbutton">
 <c:if test="${home!=1}">
 <a href="/home" type="button" class="btn btn-outline-primary">Back</a>
 
 </c:if>

</div>
</div>

<c:forEach var="service" items="${ownerServices}">
<section class="py-5">
    <div class="container">

        <div class="row">
            <div class="col-lg-6 mx-auto">

                <!-- CUSTOM BLOCKQUOTE -->
                <blockquote class="blockquote blockquote-custom bg-white p-5 shad rounded">
                  <p class="mb-0 mt-2 font-italic"> 
                
  	<p>Location:<c:out value="${service.location}"/></p>
  	                    <p>Company owner:<a href="/user/card/${service.company.contractor.id}"><c:out value="${service.company.contractor.firstName}"/> </a></p>
  	
                    <p>Description:<c:out value="${service.description}"/></p>
                    <p>starting Date<c:out value="${service.startingDate}"/></p>
                    <p>Finishing Date<c:out value="${service.finishingDate}"/></p>
                    <p>Specialization<c:out value="${service.specialization}"/></p><p>
                                        <footer class="blockquote-footer pt-4 mt-4 border-top">
                                        <c:if test="${user.id == service.owner.id}">
                                        
                        <cite title="Source Title"><a href="/services/${service.id}/edit">edit your post</a></cite>
                        </c:if>
                        
                    </footer>
                </blockquote><!-- END -->

            </div>
        </div>
    </div>
</section>
</c:forEach>


</body>
</html>




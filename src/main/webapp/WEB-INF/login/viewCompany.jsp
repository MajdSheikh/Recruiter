
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Company</title>
<link rel="stylesheet" type="text/css" href="/css/viewCompany.css">
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

<a href="/home" type="button" class="btn btn-outline-primary">Back</a>



<div >
<div>
<section class="py-5">
    <div class="container-fluid">

        <div class="row">
            <div class="col-lg-6 mx-auto">

                <!-- CUSTOM BLOCKQUOTE -->
                <blockquote class="blockquote blockquote-custom bg-white p-5 shad rounded">
                  <p class="mb-0 mt-2 font-italic"> 
                

<div class="border">    	
</div>


    	
 <h2>Apply for  Companies</h2>
 
 <form action="/company/add" method="post" >  
 
 
<div>
<h4> Members of Company  </h4>

 <c:forEach var="company1" items="${user.getEmployeeCompanies()}">
        <p >Company Title :<c:out value="${company1.title}"></c:out> <a href="/getout/${company1.id }"> Leave</a></p>
    	</c:forEach>
       
    	
</div>	
  	<p>Pick Company you wanna work with  :</p><select name="company">
        <c:forEach var="company" items="${companies}">
        <option value="${company.id}"><c:out value="${company.title}"></c:out></option>
    	</c:forEach>
	</select>
	<div class="marginTop">
	<input type="submit" value="Add" type="button" class="btn btn-outline-info my-2">
	</div>
        
</form>


                </blockquote><!-- END -->

            </div>
        </div>
    </div>
</section>

</div>



</div>


</body>
</html>




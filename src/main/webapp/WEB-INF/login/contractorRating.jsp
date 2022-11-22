
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Owner Rating</title>
<link rel="stylesheet" type="text/css" href="/css/contractorRating.css">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<script type="text/javascript" src="js/app.js"></script>

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

<div class="h">
<h3>Provide your feedback</h3>
<div class="rightbutton">
<a href="/home" type="button"> Your dashboard</a>
</div>
</div>



<section class="py-5">
    <div class="container">

        <div class="row">
            <div class="col-lg-6 mx-auto">

                <blockquote class="blockquote blockquote-custom bg-white p-5 shad rounded">
                  <p class="mb-0 mt-2 font-italic"> 
                	<div>
                	<form action="/job/rating/${service.id}" method="post" class="container">
                	<div class="rate fs-4 fw-bold">
                	</div>
                	<div>
                	<input type="number" name="rate"></input>
                	</div>
                	
         <div class="ri">
        <div class="row">
        <div class="rating ri">
          <input type="radio" id="star1" name="rating" value="1" /><label for="star1" title="Rocks!">5 stars</label>
          <input type="radio" id="star2" name="rating" value="2" /><label for="star2" title="Rocks!">4 stars</label>
          <input type="radio" id="star3" name="rating" value="3" /><label for="star3" title="Pretty good">3 stars</label>
          <input type="radio" id="star4" name="rating" value="4" /><label for="star4" title="Pretty good">2 stars</label>
          <input type="radio" id="star5" name="rating" value="5" /><label for="star5" title="Meh">1 star</label>

        </div>
        </div>
    </div>

    <input  type="submit" value="Submit" type="button" class="btn btn-warning btn-lg btn-block bn">
                	</form>
                	</div>

                </blockquote>

            </div>
        </div>
    </div>
</section>




</body>
</html>




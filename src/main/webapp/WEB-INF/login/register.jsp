<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Page</title>
<link rel="stylesheet" type="text/css" href="/css/reg.css">

<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>
<div class="container">
	<div class="d-flex justify-content-center h-100">
		<div class="card">
			<div class="card-header">
				<h3>Sign Up</h3>
					<div class="d-flex justify-content-end social_icon">
					<span><i class="fab fa-facebook-square"></i></span>
					<span><i class="fab fa-google-plus-square"></i></span>
					<span><i class="fab fa-twitter-square"></i></span>
				</div>

			</div>
			<div class="card-body">            
    <form:form action="/register" method="post" modelAttribute="newUser" class="form-horizontal">
    
   
       <div class="input-group form-group space">
 			<div class="input-group-prepend">
				<span class="input-group-text"><i class="fas fa-user"></i></span>
			</div>
           <form:input path="firstName" class="form-control" placeholder="First Name"/>
           <form:errors path="firstName" class="text-danger" />
	   </div>

       <div class="input-group form-group space">
 			<div class="input-group-prepend">
				<span class="input-group-text"><i class="fas fa-user"></i></span>
			</div>
           <form:input path="lastName" class="form-control" placeholder="Last Name"/>
           <form:errors path="lastName" class="text-danger" />
	   </div>
	   
	    <div class="input-group form-group space">
 			<div class="input-group-prepend">
				<span class="input-group-text"><i class="fas fa-user"></i></span>
			</div>
           <form:input path="email" class="form-control" placeholder="Email Address"/>
           <form:errors path="email" class="text-danger" />
	   </div>
	   
	   
	   	<div class="input-group form-group space">
 			<div class="input-group-prepend">
				<span class="input-group-text"><i class="fas fa-user"></i></span>
			</div>
           <form:input path="nationalId" class="form-control" placeholder="national ID"/>
           <form:errors path="nationalId" class="text-danger" />
	   </div>
	   
	   
	   
	   	<div class="input-group form-group space">
 			<div class="input-group-prepend">
				<span class="input-group-text"><i class="fas fa-user"></i></span>
			</div>
           <form:input path="password" class="form-control" type="password" placeholder="Password"/>
           <form:errors path="password" class="text-danger" />
	   </div>


	   	<div class="input-group form-group space">
 			<div class="input-group-prepend">
				<span class="input-group-text"><i class="fas fa-user"></i></span>
			</div>
           <form:input path="confirm" class="form-control" type="password" placeholder="confirm Password"/>
           <form:errors path="confirm" class="text-danger" />
	   </div>

      <div class="form-group d-flex d-flex justify-content-center mt-4">
			<input type="submit" value="sign up" class="btn-sm login_btn border-0">
		</div>
    </form:form>
    
		</div>
			<div class="card-footer">
				<div class="d-flex justify-content-center links color">
					already have an account?
					<div><a href="/Regpage" class="color">log in</a></div>
				</div>
				
			</div>
		</div>
	</div>
</div>
        

</body>
</html>




<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login page</title>
<link rel="stylesheet" type="text/css" href="/css/index.css">

<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
</head>
<body>

<div class="container">
	<div class="d-flex justify-content-center h-100">
		<div class="card">
			<div class="card-header">
				<h3>Sign In</h3>
					<div class="d-flex justify-content-end social_icon">
					<span><i class="fab fa-facebook-square"></i></span>
					<span><i class="fab fa-google-plus-square"></i></span>
					<span><i class="fab fa-twitter-square"></i></span>
				</div>

			</div>
			<div class="card-body">
            
	<form:form action="/login" method="post" modelAttribute="newLogin" class="form-horizontal">


        <div class="input-group form-group space">
			<div class="input-group-prepend">
				<span class="input-group-text"><i class="fas fa-user"></i></span>
			</div>
            <form:input path="email" class="form-control" placeholder="username@address.com"/>
            <form:errors path="email" class="text-danger" />
        </div>
    
        
        <div class="input-group form-group">
        	<div class="input-group-prepend">
				<span class="input-group-text"><i class="fas fa-key"></i></span>
			</div>
            <form:input path="password" class="form-control" placeholder="password" type="password"/>
            <form:errors path="password" class="text-danger" />
       </div>
       
        <div class="row align-items-center remember mar">
			<input type="checkbox">Remember Me
		</div>
		<div class="form-group d-flex flex-row-reverse mt-4">
			<input type="submit" value="Login" class="btn-sm login_btn border-0">
		</div>

    </form:form>
		</div>
			<div class="card-footer">
				<div class="d-flex justify-content-center links color">
					Don't have an account?
					<div><a href="/Regpage" class="color">Sign Up</a></div>
				</div>
				
			</div>
		</div>
	</div>
</div>
                   
        

</body>
</html>



            
				
					

	        
 

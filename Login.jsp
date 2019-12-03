<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="servlet.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="utf-8">
	<title>Teamify Login</title>
	<link rel="stylesheet" type="text/css" href="registration.css">
</head>
<body>
<div class="form-container">
	<form class="list" method="GET" action="LoginServ" >
		<h3>Teamify Login</h3>
		<input type="text" name="name" placeholder="User Name">
		<input type="text" name="password" placeholder="Password">
		
		<button value="Login" style="background-color: #75B9BE; color: #fff; margin: 30px 0px">Login </button>
	</form>
</div>
</body>
</html>




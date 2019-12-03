<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="servlet.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<title>Teamify Register</title>
	<link rel="stylesheet" type="text/css" href="registration.css">
	<title>Insert title here</title>
</head>
<body>
	<div class="form-container">
		<form class="list" action="Register" method="GET">
			<h3>Teamify Register</h3>
			<input type="text" name="username" placeholder="User Name"><br/>
			<input type="text" name="password" placeholder="Password"><br/>
			<input type="text" name="Cnofirm Password" placeholder="Confirm Password"><br/>
			<input type="text" name="email" placeholder="Email"><br/>
			<input type="text" name="description" placeholder="Description"><br/>
			<button name="Submit" style="background-color: #75B9BE; color: #fff; margin: 30px 0px">Register as Organizer</button><br/>
			<button name="Submit" style="background-color: #75B9BE; color: #fff; margin: 30px 0px">Register as Collaborator</button><br/>
		</form>
	</div>
</body>
</html>
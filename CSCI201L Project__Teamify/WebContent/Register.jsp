<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Teamify Register</title>
	    <link rel="stylesheet" href="bootstrap\bootstrap.min.css">

<link rel="stylesheet" type="text/css" href="registration.css">
<title>Insert title here</title>
</head>
<body>
	<div class="form-container">
		<form class="list" action="Register" method="GET">
			<h3>Teamify Register</h3>
			<input type="text" name="username" placeholder="User Name"> <input
				type="text" name="password" placeholder="Password"> <input
				type="text" name="password2" placeholder="Confirm Password">
			<input type="text" name="email" placeholder="Email">
			<div class="form-check">
				<input class="form-check-input" type="radio" name="exampleRadios"
					id="exampleRadios1" value="option1" checked> <label
					class="form-check-label" for="exampleRadios1"> Organizer </label>
			</div>
			<div class="form-check">
				<input class="form-check-input" type="radio" name="exampleRadios"
					id="exampleRadios2" value="option2"> <label
					class="form-check-label" for="exampleRadios2"> Collaborator </label>
			</div>
			
			<button name="Submit"
				style="background-color: #75B9BE; color: #fff; margin: 30px 0px">Register</button>
		</form>
	</div>
	 <script src="bootstrap\popper.min.js"></script>
    <script src="bootstrap\bootstrap.min.js"></script>
</body>
</html>

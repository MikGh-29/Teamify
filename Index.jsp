<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="bootstrap\bootstrap.min.css">
    <link rel="stylesheet" href="css\Index.css">

    <title>Teamify</title>
</head>

<header>
    <nav class="navbar navbar-expand-lg" style="background-color: rgba(0,0,0,0)">
        <div class="container-fluid align-items-center">
            <!-- LOGO -->
            <div>
                <a class="navbar-brand" href="#">
                    <img src="img\Logologo.png" width="40" height="40" alt="">
                </a>
            </div>

            <!-- SIDE BUTTONS -->
            <div class="d-flex flex-row align-items-center">
                <button type="button" class="btn btn-link mr-2" style="color:#4B4237">Login</button>
                <button type="button" class="btn ml-4 profile shadow-sm" style="background-color: #F5C396">Register</button>
            </div>
        </div>
    </nav>
</header>

<body>
    <div class="main m-5 pt-5">
        <h1 class="pt-5 main-text text-center">Who Are You?</h1>
        <p class="text-center second-text ">Tell us who you are and start your co-creation journey!</p>
        <div class="row justify-content-center pt-4">
            <button type="button" class="organizer btn ml-4 profile shadow" style="background-color: #75B9BE">I'm a Organizer</button>
            <button type="button" class="collaborator btn ml-4 profile shadow" style="background-color: #EE7674">I'm a Collaborator</button>

        </div>
    </div>


    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="jQuery\jquery-3.4.1.min.js"></script>
    <script src="bootstrap\popper.min.js"></script>
    <script src="bootstrap\bootstrap.min.js"></script>
</body>

</html>
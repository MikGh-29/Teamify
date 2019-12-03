<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="servlet.*" %>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="bootstrap\bootstrap.min.css">
    <link rel="stylesheet" href="tagsInputJQ\jquery.tagsinput-revisited.css">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="css\Index.css">

    <script src="jQuery\jquery-3.4.1.min.js"></script>
	<%String n = (String)session.getAttribute("name");
    %>
    <script>
    
        window.onload = function() {
            if (<%=n!=null && !n.equals("")%>) {
                document.getElementById("message").onclick = goToMessage;
                document.getElementById("message").innerHTML = "Logout";
                document.getElementById("profile").onclick = profile;
                document.getElementById("profile").innerHTML = "Profile";

                $(".main").addClass("d-none");
                $(".search").removeClass("d-none");

            } else {
                $(".main").removeClass("d-none");
                $(".search").addClass("d-none");
            }

            if (getCookie("accountType") == "organizer") {
                document.getElementsByClassName("search-form").action = "OrganizerSearchResult.jsp";
            } else if (getCookie("accountType") == "collaborator") {
                document.getElementsByClassName("search-form").action = "SearchResult.jsp";
            }
        }

        function goToMessage() {
            window.location = 'LogoutServ';
        }

        function goToLogin() {
            window.location = 'Login.jsp';
        }

        function goToRegister() {
            window.location = 'Register.jsp';
        }

        function getCookie(name) {
            var re = new RegExp(name + "=([^;]+)");
            var value = re.exec(document.cookie);
            return (value != null) ? unescape(value[1]) : null;
        }
    </script>

    <title>Teamify</title>

</head>
<!-- NAV BAR -->


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
                <button id="message" type="button" class="btn btn-link mr-2" style="color:#4B4237" onclick="goToLogin()">Login</button>
                <button id="profile" type="button" class="btn ml-4 profile shadow-sm" style="background-color: #F5C396" onclick="goToRegister()">Register</button>
            </div>
        </div>
    </nav>
</header>

<body>
    <div class="main m-5 pt-5">
        <h1 class="pt-5 main-text text-center">Who Are You?</h1>
        <p class="text-center second-text ">Tell us who you are and start your co-creation journey!</p>
        <div class="row justify-content-center pt-4">
            <button type="button" class="organizer btn ml-4 profile shadow" style="background-color: #75B9BE" onclick="goToRegister()">I'm a Organizer</button>
            <button type="button" class="collaborator btn ml-4 profile shadow" style="background-color: #EE7674" onclick="goToRegister()">I'm a Collaborator</button>

        </div>
    </div>

    <div class="search d-none">
        <form class="search-form" action="SearchServ" method="get">
            <div class="search-bar">
                <div class="p-2 ">
                    <input id="search" name="search" type="text">
                </div>
                <div class="float-right mr-2">
                    <button class="btn" style="background-color: #EE7674" type="submit" onclick="generateSearchArray()">Search</button>
                </div>
            </div>
        </form>
    </div>


    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="bootstrap\popper.min.js"></script>
    <script src="bootstrap\bootstrap.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js " integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU=" crossorigin=" anonymous "></script>
    <script src="tagsInputJQ\jquery.tagsinput-revisited.js "></script>

    <!-- TAGS INPUT + AUTOCOMPLETE SCRIPT -->
    <script>
        $('#search').tagsInput({
            minChars: 0,
            maxChars: null,
            limit: null,
            unique: true,
            placeholder: 'Enter tag plz',
            'autocomplete': {
                source: [
                    'C++',
                    'Java',
                    'Music',
                    'Game',
                    'Remote Friendly',
                    'Experience Required',
                    'Unity',
                    'Long-term (> 1 year)',
                    'Social Impact',
                    'Business',
                    'Marketing',
                    'Machine Learning',
                    'Prototype',
                    'Compensation',
                    'Small Team (1-6)',
                    'Engineering'
                ]
            }
        });
    </script>

    <script>
        function generateSearchArray() {
            var tagText = document.getElementsByClassName("tag-text");
            var x = document.getElementsByClassName("tag-text").length;
            var textArray = new Array();
            for (var i = 0; i < x; i++) {
                textArray.push(tagText[i].innerHTML);
            }
        }
    </script>
</body>

</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <!-- CSS (jQuery) -->
    <link rel="stylesheet" href="tagsInputJQ\jquery.tagsinput-revisited.css">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="css\test.css">

    <title>Teamify</title>
</head>
<body>
    <!-- NAV BAR -->

    <nav class="navbar navbar-expand-lg shadow-sm" style="background-color: #FFF5F3">
        <div class="container-fluid align-items-center">
            <!-- LOGO -->
            <div>
                <a class="navbar-brand" href="#">
                    <img src="img\Logologo.png" width="40" height="40" alt="">
                </a>
            </div>

            <!-- SEARCH BOX -->
            <form action="www.google.com">
                <div class="d-flex flex-row align-items-center ">
                    <div class="p-2 ">
                        <input id="search" name="search" type="text">
                    </div>
                    <div class="">
                        <button class="btn" style="background-color: #EE7674" type="submit">Search</button>
                    </div>
                </div>
            </form>

            <!-- SIDE BUTTONS -->
            <div class="d-flex flex-row align-items-center">
                <button type="button" class="btn btn-link mr-2" style="color: grey">Messages</button>
                <button type="button" class="btn ml-4 profile" style="background-color: #F5C396">Profile</button>
            </div>
        </div>
    </nav>

    <div class="container pt-5">
        <div class="row">
            <div class="col-4 cover-image">
                <img src="img\lightbulb (1).png" class="rounded float-left ml-5 mt-4 py-3 pl-5 w-75" alt="...">
            </div>
            <div class="col-auto">
                <h1 class="p-4 float-left">title</h1>
            </div>
        </div>
    </div>

    <!-- Optional BS JavaScript -->
    <script src=" https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js "></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js " integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49 " crossorigin="anonymous "></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js " integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy " crossorigin="anonymous "></script>
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
</body>

</html>
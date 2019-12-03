<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="java.util.ArrayList"%>
<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <!-- CSS (jQuery) -->
    <link rel="stylesheet" href="tagsInputJQ\jquery.tagsinput-revisited.css">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="css\test.css">

	<!-- Profile Display CSS -->
<style class="cp-pen-styles">@import url(https://fonts.googleapis.com/icon?family=Material+Icons);
@import url("https://fonts.googleapis.com/css?family=Raleway");
.profile-display {
  width: 800px;
  margin: 40px auto;
}

.box {
  float: left;
  display: block;
  width: 150px;
  height: 150px;
  margin: 10px;
  background-color: white;
  border-radius: 5px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.12), 0 1px 2px rgba(0, 0, 0, 0.24);
  -webkit-transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);
  transition: all 0.3s cubic-bezier(0.25, 0.8, 0.25, 1);
  overflow: hidden;
}

.profile-image {
  height: 150px;
  width: 100%;
  position: relative;
  overflow: hidden;
  background-image: url("");
  background-color: white;
  background-position: center center;
  background-repeat: no-repeat;
  background-size: cover;
}

#username, #email, #description {
  margin: 20px 200px;
}
.profile-info {
  width: 500px;
}
</style>

    <title>Profile</title>
</head>

<body>

    <!-- NAV BAR -->
	<% ArrayList<String> t = new ArrayList<String>();
	t.add("1");
	t.add("2");
	session.setAttribute("Tags", t); 
	session.setAttribute("userType", "a");%>
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
                <button type="button" class="btn btn-link" style="color: grey">Messages</button>
                <button type="button" class="btn" style="background-color: #F5C396">Profile</button>
            </div>
        </div>
    </nav>

    <hr class="divider">

    <!-- PROFILE DISPLAY -->
    <div class="profile-display">
    
    	<!-- PROFILE PIC -->
    	<div class="box">
    		<div class="profile-image"></div>
    	</div>
    	
    	<!-- PROFILE INFOS -->
    	<div class="profile-info">
    		<div id="username">
    			<%= session.getAttribute("username") %>
    		</div>
    		<div id="tags">
    			<%	ArrayList<String> tags = (ArrayList<String>) session.getAttribute("Tags"); 
    				for (int i = 0; i < tags.size(); i++) {
    			%>
    				<%= tags.get(i) %>
    			<% 
    				} 
    			%>
    		</div>
    		<div id="email">
    			<%= session.getAttribute("email") %>
    		</div>
    		<div id="description">
    			<%= session.getAttribute("description") %>
    		</div>
    	</div>
	</div>

    <hr class="divider">
    
    <!-- PROJECT LISTINGS -->
    <div id="project-listing"></div>

    <!-- Optional BS JavaScript -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js" integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU=" crossorigin="anonymous"></script>
    <script src="tagsInputJQ\jquery.tagsinput-revisited.js"></script>

	<!-- PROJECT LISTING SCRIPT -->
	<script>
		var type = <%= session.getAttribute("userType").toString()%>;
		if (type == "organizer"){
			var projects = <%= session.getAttribute("projects")%>;
			for (int i = 0; i < type.size(); i++) {
				$("#project-listing").append(projects.get(i));
			}
		}
	</script>

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
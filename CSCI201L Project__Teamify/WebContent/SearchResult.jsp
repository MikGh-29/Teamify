<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.*, servlet.Project, servlet.User"%>

<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<!-- CSS (jQuery) -->
<link rel="stylesheet" href="tagsInputJQ\jquery.tagsinput-revisited.css">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="css\Details.css">

<title>Teamify</title>

<script>
	function createProject() {
		window.location = 'CreateListing.jsp';
	}
	
	function goToProfile() {
		window.location = 'Profile.jsp';
	}
</script>
</head>

<body>

	<!-- NAV BAR -->

	<nav class="navbar navbar-expand-lg shadow-sm"
		style="background-color: #FFF5F3">
		<div class="container-fluid align-items-center">
			<!-- LOGO -->
			<div>
				<a class="navbar-brand" href="#"> <img src="img\Logologo.png"
					width="40" height="40" alt="">
				</a>
			</div>

			<!-- SEARCH BOX -->
			<form action="SearchServ" method="get">
				<div class="d-flex flex-row align-items-center ">
					<div class="p-2 ">
						<input id="search" name="search" type="text">
					</div>
					<div class="">
						<button class="btn" style="background-color: #EE7674"
							type="submit">Search</button>
					</div>
				</div>
			</form>

			<!-- SIDE BUTTONS -->
			<div class="d-flex flex-row align-items-center">
				<button type="button" class="btn btn-link mr-2" style="color: grey" onclick="createProject()">Create Project</button>
				<button type="button" class="btn ml-4 profile"
					style="background-color: #F5C396" onclick="goToProfile()">Profile</button>
			</div>
		</div>
	</nav>

	<hr class="divider">

	<!-- RESULT DISPLAY OPTIONS -->
	<div class="display-setting container align-items-center p-2">
		<div class="row ">
			<div class="col-1.5 ">
				<div class="dropdown">
					<button class="btn btn-sm dropdown-toggle"
						style="background-color: rgba(152, 114, 132, 0.4); color: #4B4237"
						type="button" id="dropdownMenuButton" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false">Sort by
						popularity</button>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<a class="dropdown-item" href="#">Action</a> <a
							class="dropdown-item" href="#">Another action</a> <a
							class="dropdown-item" href="#">Something else here</a>
					</div>
				</div>
			</div>
			<div class="col-2">
				<div class="dropdown">
					<button class="btn btn-sm dropdown-toggle"
						style="background-color: rgba(152, 114, 132, 0.4); color: #4B4237"
						type="button" id="dropdownMenuButton" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false">5 results per
						page</button>
					<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
						<a class="dropdown-item" href="#">Action</a> <a
							class="dropdown-item" href="#">Another action</a> <a
							class="dropdown-item" href="#">Something else here</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<hr class="divider">

	<!-- SEARCH RESULTS -->
	<div class="container-fluid seach-result-container">
		<div class="container pt-4" id="results">
			<div class="row">
				<div class="card shadow-lg mb-3" style="max-width: 70%;">
					<div class="row no-gutters position-relative">
						<div class="col-md-4 p-4 ">
							<img src="img\lightbulb%20(1).png" class="card-img" alt="...">
						</div>
						<div class="col-md-8 mt-3 position-static">
							<div class="card-body">
								<a class="stretched-link" href="Details.html">
									<h5 class="card-title">Game Startup Idea</h5>
								</a>
								<p class="card-text">We want to build a startup that charges people for premium all-inclusivve gaming expeirence at home.</p>
								<p class="card-text">
									<small class="text-muted">Organized by Xiao Ming</small>
								</p>
							</div>
							<div class="row pl-3 mt-2">
								<div class="pb-4 pl-1 ml-3">
									<button type="button" class="btn" data-toggle="modal"
										data-target="#messageModal"
										style="color: #F9F1F0; background-color: #F5C396">Customize
										Invite</button>
								</div>
								<div class="pb-4 pl-1 ml-3">
									<button type="button" class="btn"
										style="color: #F9F1F0; background-color: #EE7674">Interested!</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<%
				String type = session.getAttribute("type").toString();
			%>

			<%
				if (type.equalsIgnoreCase("contributor")) {
					System.out.println("In contributor");
					List<Project> resultList = (List<Project>) request.getAttribute("projectResult");
					if (resultList == null || resultList.size() == 0) {
						System.out.println("Empty list");
					} else {
						System.out.println("Good project list");
						System.out.println(resultList.size());
					}

					for (int i = 0; i < resultList.size(); i++) {
			%>
			<div class="row">
				<div class="card shadow-lg mb-3" style="max-width: 70%;">
					<div class="row no-gutters position-relative">
						<div class="col-md-4 p-4 ">
							<img src="img\lightbulb%20(1).png" class="card-img" alt="...">
						</div>
						<div class="col-md-8 mt-3 position-static">
							<div class="card-body">
								<a class="stretched-link" href="Details.html">
									<h5 class="card-title"><%=resultList.get(i).name%></h5>
								</a>
								<p class="card-text"><%=resultList.get(i).description%></p>
								<p class="card-text">
									<small class="text-muted">Organized by Xiao Ming</small>
								</p>
							</div>
							<div class="row pl-3 mt-2">
								<div class="pb-4 pl-1 ml-3">
									<button type="button" class="btn" data-toggle="modal"
										data-target="#messageModal"
										style="color: #F9F1F0; background-color: #F5C396">Customize
										Invite</button>
								</div>
								<div class="pb-4 pl-1 ml-3">
									<button type="button" class="btn"
										style="color: #F9F1F0; background-color: #EE7674">Interested!</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
	<%
		}
		} else if (type.equalsIgnoreCase("organizer")) {
			System.out.println("In user");
			List<User> users = (List<User>) request.getAttribute("userResult");
			if (users == null) {
				System.out.println("Empty user list");
			} else {
				System.out.println("Good user list");
				System.out.println("User num " + users.size());

			}

			for (int i = 0; i < users.size(); i++) {
	%>

	<div class="row">
		<div class="card shadow-lg mb-3" style="max-width: 70%;">
			<div class="col-md-4 p-4 ">
				<img src="img\lightbulb.png" class="card-img" alt="" />
			</div>
			<div class="col-md-8 mt-3 position-static">
				<div class="card-body">
					<a class="stretched-link" href="Details.html">
						<h5 class="card-title">
							<%=users.get(i).name%>
						</h5>
					</a>
					<p class="card-text">
						<%=users.get(i).description%>
					</p>
				</div>
				<div class="row pl-3 mt-2">
					<div class="pb-4 pl-1 ml-3">
						<button type="button" class="btn" data-toggle="modal"
							data-target="#messageModal"
							style="color: #F9F1F0; background-color: #F5C396">Customize
							Invite</button>
					</div>
					<div class="pb-4 pl-1 ml-3">
						<button type="button" class="btn"
							style="color: #F9F1F0; background-color: #EE7674">Interested!</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	</div>
	<%
		}
		}
	%>

	</div>
	</div>
	
	
	<!-- <div class="position-fixed">
	HIIIIIII
		<i class="fas fa-plus-circle fa-2x" style="color: #F5C396"></i>
	</div>
 -->
	<!-- MODAL -->

	<div class="modal fade" id="messageModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">New message</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<form>
						<div class="form-group">
							<label for="recipient-name" class="col-form-label">Recipient:</label>
							<input type="text" class="form-control" id="recipient-name">
						</div>
						<div class="form-group">
							<label for="message-text" class="col-form-label">Message:</label>
							<textarea class="form-control" id="message-text"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn"
						style="color: #F9F1F0; background-color: rgba(152, 114, 132, 0.4); color: grey"
						data-dismiss="modal">Close</button>
					<button type="button" class="btn" data-dismiss="modal"
						style="color: #F9F1F0; background-color: #EE7674">Send
						message</button>
				</div>
			</div>
		</div>
	</div>

	<!-- Optional BS JavaScript -->
	<script
		src=" https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js "></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js "
		integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49 "
		crossorigin="anonymous "></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js "
		integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy "
		crossorigin="anonymous "></script>
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js "
		integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU="
		crossorigin=" anonymous "></script>
	<script src="tagsInputJQ\jquery.tagsinput-revisited.js "></script>
	<script src="https://kit.fontawesome.com/f930095f9b.js" crossorigin="anonymous"></script>



	<!-- TAGS INPUT + AUTOCOMPLETE SCRIPT -->
	<script>
		$('#search').tagsInput(
				{
					minChars : 0,
					maxChars : null,
					limit : null,
					unique : true,
					placeholder : 'Enter tag plz',
					'autocomplete' : {
						source : [ 'C++', 'Java', 'Music', 'Game',
								'Remote Friendly', 'Experience Required',
								'Unity', 'Long-term (> 1 year)',
								'Social Impact', 'Business', 'Marketing',
								'Machine Learning', 'Prototype',
								'Compensation', 'Small Team (1-6)',
								'Engineering' ]
					}
				});
	</script>
</body>

</html>
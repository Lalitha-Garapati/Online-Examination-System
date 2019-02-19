<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page language="java" contentType="text/html"%>
<head>
<title>Online Examination:Admin/Home Page</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<style>
@import url(layout.css);
</style>
</head>
<body>

	<div id="container">
		<div id="header">
			
			<div class="pull-right">
				<div class="btn">
					<a href="HomeController?action=logout" class="btn btn-danger"
						role="button"> Logout</a>
				</div>
			</div>
			
		</div>
		${sessionScope.success}
		<div id="content">
			<div id="main1">
			<p>${successMessage}${errorMessage}</p>

				<form action="TestController" enctype="multipart/form-data"
					method="post">
					<b><p>Name :</p></b><input type="text" name="name"
						placeholder="Enter Name" required /><br> <br> <b><p>Start
							Date :</p></b> <input type="date" name="startDate"
						placeholder="Enter Start Date" required /><br> <br> <b><p>End
							Date :</p></b> <input type="date" name="endDate"
						placeholder="Enter End Date" required /> <b><p>Duration :</p></b>
					<input type="number" name="duration" placeholder="Enter Duration"
						required /><br> <br> <input type="file" name="file"
						align="middle" required />
					<button type="submit" class="btn btn-primary" value="UserSubmit"
						data-dismiss="modal">Submit</button>
				</form>
			</div>
			<div id="navigation">
				<a href="HomeController?action=create_test" class="btn btn-info btn-block"
					role="button" value="CreateTest">Create Test</a><br> <br>
				<a href="HomeController?action=admin" class="btn btn-info btn-block"
					role="button" value="CreateAdmin">Create Admin</a><br> <br>
				<a href="HomeController?action=view" class="btn btn-info btn-block"
					role="button" value="ViewTests">View Tests</a><br> <br> <a
					href="HomeController?action=enroll" class="btn btn-info btn-block"
					role="button" value="EnrollUsers">Enroll Users</a><br> <br>
				<a href="HomeController?action=user" class="btn btn-info btn-block"
					role="button" value="CreateUsers">Create Users</a><br> <br>
			</div>
		</div>
		<div id="footer">
			<br>
			<p>@ Copyrights Reserved</p>
			<br> <br>
		</div>
	</div>
</body>
</html>
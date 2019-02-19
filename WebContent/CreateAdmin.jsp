<%@ page language="java" contentType="text/html"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Online Examination:Admin/Create Users</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<style>
@import url(layout.css);
</style>
</head>
<body>
	<div id="container">
		<div id="header">
			<div class="pull-right">
				<div class="btn">
					<form action="HomeController">
						<a href="HomeController?action=logout" class="btn btn-danger"
						role="button"> Logout</a>
					</form>
				</div>
			</div>
		</div>
		<div id="content">
			<div id="main1">
				<form action="HomeController">
					<br> <h3>Name : <input type="text" id="name"
						placeholder=" Enter AdminName" required></h3><br>
					<h3>Password : <input type="password" id="password" placeholder=" Enter Password"
						required></h3><br><h3>Email : <input type="email"
						id="email" placeholder=" @gmail.com" required></h3><br>
					<h3>Contact No. : <input type="text" id="mobileno"
						placeholder=" Enter Contact Number" required></h3><br> <input
						type="submit" value="Add"  class="btn btn-info"/><input type="hidden" name="action"
						value="admin"/>
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
			<br> <br>
			<P>@ Copyrights Reserved</P>
			<br> <br>
		</div>
	</div>
</body>
</html>
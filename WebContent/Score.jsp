<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<style>
@import url(layout.css);
</style>

<title>Insert title here</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
</head>
<body>
	<div id="container">
		<div id="header"></div>
		<div id="main1">
			<form>
				<h1>Your score is: ${sessionScope.scores} on
					${sessionScope.questionsList.size()}</h1>
				<a href="UserHomePage.html">HOME </a>
			</form>
		</div>
		<div id="footer">
			<br> <br>
			<p>@ CopyRights Reserved</p>
			<br> <br>
		</div>
	</div>
</body>
</html>
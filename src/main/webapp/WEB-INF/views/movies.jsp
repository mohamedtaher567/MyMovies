<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<!-- Access the bootstrap Css like this, 
		Spring boot will handle the resource mapping automcatically -->
<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<!-- 
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Spring Boot</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#">Home</a></li>
					<li><a href="#about">About</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">

		<div class="starter-template">
			<h1>Movies</h1>
		</div>


	</div>

	<div class="container" style="overflow-x: auto;">
		<table style="width: 100%">
			<tr>
				<th></th>
				<th></th>
				<th>Movie Name</th>
				<th>Release Date</th>
				<th>Language</th>
			</tr>
			<c:forEach items="${movies}" var="movies">
				<tr>
					<td align="center"><button class ="add_to_watch"/></button></td>
					<td align="center"><img
						src="https://image.tmdb.org/t/p/original${movies.logoPath}"
						style="width: 150px; height: 200px;" /></td>
					<td align="center">${movies.name}</td>
					<td align="center">${movies.year}</td>
					<td align="center">${movies.language}</td>
				</tr>
			</c:forEach>

		</table>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js"></script>

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(document).ready(
				function() {
					$('.add_to_watch').click(
							function() {
								$.ajax({
									type : "post",
									url : "add_to_watch",
									data : "name=" + "movie_name"
											+ "&year=" + "movie_year"
								});
							});

				});
	</script>

</body>

</html>
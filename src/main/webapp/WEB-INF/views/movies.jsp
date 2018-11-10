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
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a class="navbar-brand" href="watched">Watched
							movies</a></li>
					<li><a class="navbar-brand" href="want_to_watch">Want to
							watch movies</a></li>
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
		<c:if test="${pageNumber != null}">
			<a href="${pageNumber-1}">Previous</a>
			<a href="${pageNumber+1}">Next</a>
		</c:if>

		<table style="width: 100%">
			<tr>
				<c:if test="${pageNumber == null}">
					<th></th>
				</c:if>
				<th></th>
				<th>Movie Name</th>
				<th>Release Date</th>
				<th>Language</th>
			</tr>
			<c:forEach items="${movies}" var="movies">
				<tr>
					<td align="center"><c:choose>
							<c:when test="${pageNumber == null}">
								<c:choose>
									<c:when test="${wantToWatch}">
										<button id="${movies.id}" class="remove_from_want_to_watch" />Remove</button>
									</c:when>
									<c:otherwise>
										<button id="${movies.id}" class="remove_from_watch" />Remove</button>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:otherwise>
								<button id="${movies.id}" class="add_to_watch" />Add to watched</button>
								<button id="${movies.id}" class="add_to_want_to_watch" />Add to want to watch</button>
							</c:otherwise>
						</c:choose></td>
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
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.js"></script>

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('.add_to_watch').click(function() {
				$.ajax({
					type : "post",
					url : "add_to_watch?id=" + $(this).attr('id')
				});
			});
			$('.remove_from_watch').click(function() {
				$.ajax({
					type : "post",
					url : "remove_from_watch?id=" + $(this).attr('id')
				});
				location.reload();
			});
			$('.add_to_want_to_watch').click(function() {
				$.ajax({
					type : "post",
					url : "add_to_want_to_watch?id=" + $(this).attr('id')
				});
			});
			$('.remove_from_want_to_watch').click(function() {
				$.ajax({
					type : "post",
					url : "remove_from_want_to_watch?id=" + $(this).attr('id')
				});
				location.reload();
			});
		});
	</script>

</body>

</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Institution Preview Page</title>

	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.0.0/css/font-awesome.css" />

</head>

<body>
	<header>
		<nav class="navbar navbar-default">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="index.jsp">Institution portal</a>
				</div>
			</div>
		</nav>
	</header>

	<div>
		<h2>List of Institutions</h2>
	</div>

	<div>
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Title</th>
						<th>Description</th>
						<th>Location</th>
						<th>Url</th>
						<th>Branches</th>
					</tr>
				</thead>
				<c:forEach items="${institutes}" var="Institute">
					<tbody>
					<tr>
						<td><a href="/CombinedAssignment/ListCourses?title=${Institute.getTitle()}">${Institute.getTitle()}</a></td>
						<td>${Institute.getDescription()}</td>
						<td>${Institute.getLocation()}</td>
						<td>${Institute.getUrl()}</td>
						<td>${Institute.getBranches()}</td>
					</tr>
					</tbody>
				</c:forEach>
			</table>
		</div>
	</div>
	<!-- <a href="index.jsp">Home</a> -->
</body>
</html>
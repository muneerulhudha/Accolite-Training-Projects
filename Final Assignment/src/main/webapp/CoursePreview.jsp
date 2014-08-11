<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Course Preview Page</title>

	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.0.0/css/font-awesome.css" />

	<script type="text/javascript" src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script type="text/javascript">


        $(document).ready(function () { //
            $(".longString").each(function () {
                var maxwidth = 100;
                if ($(this).text().length > maxwidth) {
                    $(this).text($(this).text().substring(0, maxwidth));
                    $(this).html($(this).html() + '...');
                }
            });
        });
    </script>

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
		<h3> Course List </h3>
		<div class="table-responsive">
			<table class="table table-hover">
				<thead>
					<tr>
						<!-- <th>ID</th> -->
						<th>Name</th>
						<th>Description</th>
					</tr>
				</thead>
				<c:forEach items="${courses}" var="Course">
					<tbody>
					<tr>
						<%-- <td>${Course.getId()}</td> --%>
						<td><a href="/CombinedAssignment/ListCoursesDetailed?courseName=${Course.getCourseName()}">${Course.getCourseName()}</a></td>
						<td class="longString">${Course.getCourseDescription()}</td>
					</tr>
					</tbody>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Course Description Detailed</title>

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
		<h3> Course Detailed Description </h3>

		<div class="well">
			<c:forEach items="${courses}" var="Course">
				<h2>${Course.getCourseName()}</h2> <br /><br />
				<p>${Course.getCourseDescription()}</p><br /><br />

				<b>Duration of the Course (in years) :</b> ${Course.getDuration()} <br /><br />
				<b>Admission Process : </b> ${Course.getAdmissionProcess()} <br /><br />
				<b>Eligibility Criteria : </b> ${Course.getEligibilityCriteria()} <br /><br />
			</c:forEach>
		</div>

	</div>
</body>
</html>
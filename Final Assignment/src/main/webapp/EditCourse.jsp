<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Select Institute</title>

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

	<form class="form-horizontal" action="/CombinedAssignment/EditCourse1">
	

		<!-- Form Name -->
		<legend>Edit Course</legend>

		<!-- Select Basic -->
		<div class="form-group">
		  <label class="col-md-4 control-label">Institute Title</label>
		  <div class="col-md-4">
		    <select name="title" class="form-control">
		      	<c:forEach items="${institutes}" var="institute">
					<option value="${institute.getTitle()}">${institute.getTitle()}</option>
				</c:forEach>
		    </select>
		  </div>
		</div>

		<div class="form-group">
		  <label class="col-md-4 control-label"></label>
		  <div class="col-md-4">
		    <input type="submit" value="Next" class="btn btn-primary">
		  </div>
		</div>
	
	</form>

</body>
</html>
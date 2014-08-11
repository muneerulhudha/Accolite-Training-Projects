<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Enter JSON content</title>

	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" />
	<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.0.0/css/font-awesome.css" />
</head>
<body>

	<header>
		<nav class="navbar navbar-default">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">Institution portal</a>
				</div>
			</div>
		</nav>
	</header>

<form action="/CombinedAssignment/ImportJSON">
	<input type="hidden" name="value" value="1"/>
	<div class="form-group">
		<label class="col-md-4 control-label">Enter JSON</label>  
		<div class="col-md-4">
			<textarea class="form-control" columns="500" id="description" name="description" placeholder="Enter JSON data"></textarea>
		</div>
	</div>

	<div class="form-group">
		<label class="col-md-4 control-label"></label>
		<div class="col-md-4">
			<input type="submit" value="Submit" class="btn btn-primary">
	  	</div>
	</div>

</form>

</body>
</html>
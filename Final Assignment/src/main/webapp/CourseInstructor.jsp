<html>
<head>
	
	<title>
		Course Instructor DashBoard
	</title>

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
	
	<div>
		<h2>Course Instructor Dashboard </h2>
	</div>

	<div>
		<div class="well">
			<center>
				<a class="btn btn-success btn-md" href="AddInstitution.jsp">Add Institution</a>
				<br /><br />
				<a class="btn btn-success btn-md" href="EditInstitution">Edit Institutions</a>
				<br /><br />
				<a class="btn btn-success btn-md" href="AddCourse.jsp">Add Courses</a>
				<br /><br />
				<a class="btn btn-success btn-md" href="EditCourse">Edit Courses</a>
				<br /><br />
				<br /><br />
				<a class="btn btn-success btn-md" href="ExportXML">Export Institution details as XML</a>
				<br /><br />
				<a class="btn btn-success btn-md" href="ExportXMLCourse">Export Course Details as XML</a>
				<br /><br />
				<a class="btn btn-success btn-md" href="ExportJSON?value=1">Export Institute details as JSON</a>
				<br /><br />
				<a class="btn btn-success btn-md" href="ExportJSON?value=2">Export Course Details as JSON</a>
				<br /><br />
				<a class="btn btn-success btn-md" href="getJSON.jsp">Import Institute details using JSON</a>
				<br /><br />
				<a class="btn btn-success btn-md" href="getJSONCourses.jsp">Import Course details using JSON</a>
				<br /><br />
				<a class="btn btn-success btn-md" href="getXMLInstitute.jsp">Import Institute details using XML</a>
				<br /><br />
				<a class="btn btn-success btn-md" href="getXMLCourse.jsp">Import Course Details using XML</a>
				<br /><br />
			</center>
		</div>
	</div>

</body>
</html>

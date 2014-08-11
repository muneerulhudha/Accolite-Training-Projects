<html>

<head>
	<title>Add Course</title>

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

	<form class="form-horizontal" action = "/CombinedAssignment/AddCourse">
		<fieldset>

			<!-- Form Name -->
			<legend>Add Course</legend>

			<!-- Text input-->
			<div class="form-group">
			  <label class="col-md-4 control-label">Enter Name</label>  
			  <div class="col-md-4">
			  <input id="name" name="name" type="text" placeholder="Name of the Course" class="form-control input-md" required>
			  </div>
			</div>

			<!-- Text input-->
			<div class="form-group">
			  <label class="col-md-4 control-label">Enter Description</label>  
			  <div class="col-md-4">
			  <textarea class="form-control" id="description" name="description" placeholder="Enter Description"></textarea>
			  </div>
			</div>

			<!-- Text input-->
			<div class="form-group">
			  <label class="col-md-4 control-label">Duration of course</label>  
			  <div class="col-md-4">
			  <input id="duration" name="duration" type="text" placeholder="in years" class="form-control input-md" required>
			  </div>
			</div>

			<!-- Text input-->
			<div class="form-group">
			  <label class="col-md-4 control-label">Admission Process</label>  
			  <div class="col-md-4">
			  <input id="admission" name="admission" type="text" placeholder="Preferred method for admission" class="form-control input-md" required>
			  </div>
			</div>

			<!-- Text input-->
			<div class="form-group">
			  <label class="col-md-4 control-label">Eligibility Criteria</label>  
			  <div class="col-md-4">
			  <input id="criteria" name="criteria" type="text" placeholder="Minimum precentage required" class="form-control input-md" required>
			  </div>
			</div>			

			<!-- Text input-->
			<div class="form-group">
			  <label class="col-md-4 control-label">Enter Institution</label>  
			  <div class="col-md-4">
			  <input id="title" name="title" type="text" placeholder="Institution Title" class="form-control input-md" required>
			  </div>
			</div>			

			<div class="form-group">
			  <label class="col-md-4 control-label"></label>
			  <div class="col-md-4">
			    <input type="submit" value="Add Course" class="btn btn-primary">
			  </div>
			</div>

		</fieldset>
	</form>
</body>
<html>

<head>
	<title>Add Institution</title>

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

	<form class="form-horizontal" action = "/CombinedAssignment/AddInstitution">
		<fieldset>

			<!-- Form Name -->
			<legend>Add Institution</legend>

			<!-- Text input-->
			<div class="form-group">
			  <label class="col-md-4 control-label">Enter Title</label>  
			  <div class="col-md-4">
			  <input id="title" name="title" type="text" placeholder="Enter Name of Institution" class="form-control input-md" required>
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
			  <label class="col-md-4 control-label">Enter Location</label>  
			  <div class="col-md-4">
			  <input id="location" name="location" type="text" placeholder="Location" class="form-control input-md" required>
			  </div>
			</div>

			<!-- Text input-->
			<div class="form-group">
			  <label class="col-md-4 control-label">Enter Branches</label>  
			  <div class="col-md-4">
			  <input id="branches" name="branches" type="text" placeholder="Branches" class="form-control input-md" required>
			  </div>
			</div>

			<!-- Text input-->
			<div class="form-group">
			  <label class="col-md-4 control-label">Enter Image Url</label>  
			  <div class="col-md-4">
			  <input id="imageurl" name="imageurl" type="text" placeholder="Enter an image Url" class="form-control input-md" required>
			  </div>
			</div>			

			<div class="form-group">
			  <label class="col-md-4 control-label"></label>
			  <div class="col-md-4">
			    <input type="submit" value="submit" class="btn btn-primary">
			  </div>
			</div>

		</fieldset>
	</form>
</body>
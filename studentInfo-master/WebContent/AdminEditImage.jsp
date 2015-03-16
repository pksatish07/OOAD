<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Image</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap.css">
<script src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="panel panel-default">
		<div class="panel-body" style="background: #D3D3D3">
			<div class="alert alert-info" style="background: #999999">
				<h1 align="center" style="color: white;">Edit Image</h1>


				<div class="well">
					<form action="updateimage" method="post"
						enctype="multipart/form-data">

						<br>

						<div class="row">
							<div class="col-md-4"></div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="myFile">Upload your photo</label> <input
										type="file" name="fileUpload" />
								</div>
							</div>
							<div class="col-md-4"></div>

							<div class="row">
								<div class="col-md-4"></div>
								<div class="col-md-4">
									<input type="submit" class="btn btn-primary" value="Update" />
								</div>
								<div class="col-md-4"></div>
							</div>

						</div>



					</form>
					<br> <a href="adminhome.jsp"> BACK </a>
				</div>
			</div>

		</div>
	</div>

</body>
</html>
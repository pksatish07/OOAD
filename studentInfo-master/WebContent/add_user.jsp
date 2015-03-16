<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap.css">

<script src="bootstrap/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add users</title>
<script type="text/javascript">
	function student() {
		var x = document.getElementById("mySelect");

		if ((x.options[x.selectedIndex].text) != 'S') {
			//document.getElementById("student_id").disabled=true;
			//document.getElementById("roll_no").disabled=true;
			//document.getElementById("hostel_addr").disabled=true;
			//document.getElementById('roll_no').style.visibility = 'visible';        

		}
	}
</script>

</head>
<body>


	<div class="container-fluid">
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-6">
				<h1>
					<font color="white">Student Information center</font>
				</h1>
			</div>
			<div class="col-md-2"></div>
		</div>
		<br>
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<h2>Add the user details</h2>
			</div>
			<div class="col-md-4"></div>
		</div>
		<br> <br>
		<div class="col-md-8 col-md-offset-2">
			<div class="well">
				<form action="adduser" method="post" enctype="multipart/form-data">

					<br>

					<div class="row">

						<div class="col-md-4"></div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="name">Username:</label> <input type="text"
									class="form-control" name="username" id="username">
							</div>
						</div>
						<div class="col-md-4"></div>
					</div>
					<br>
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="sid">Student Name:</label> <input type="text"
									class="form-control" name="name" id="name">
							</div>
						</div>
						<div class="col-md-4"></div>
					</div>
					<br>
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="sid">Email Id:</label> <input type="text"
									class="form-control" name="emailId" id="emailId">
							</div>
						</div>
						<div class="col-md-4"></div>
					</div>
					<br>
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="sid">Password:</label> <input type="password"
									class="form-control" name="password" id="password">
							</div>
						</div>
						<div class="col-md-4"></div>
					</div>
					<br>
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="sid">Roll No:</label> <input type="text"
									class="form-control" name="roll_no" id="roll_no">
							</div>
						</div>
						<div class="col-md-4"></div>
					</div>
					<br>
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="sid">Hostel Addr:</label> <input type="text"
									class="form-control" name="hostel_addr" id="hostel_addr">
							</div>
						</div>
						<div class="col-md-4"></div>
					</div>
					<br>
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="sid">Current Sem:</label> <input type="text"
									class="form-control" name="current_sem" id="current_sem">
							</div>
						</div>
						<div class="col-md-4"></div>
					</div>
					<br>
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="sid">Date of Birth (mm/dd/yyyy):</label> <input
									type="date" class="form-control" name="dob" id="dob">
							</div>
						</div>
						<div class="col-md-4"></div>
					</div>
					<br>
					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<div class="form-group">
								<label for="myFile">Upload your photo</label> <input type="file"
									name="fileUpload" />
							</div>
						</div>
						<div class="col-md-4"></div>
					</div>

					<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<input type="submit" class="btn btn-primary" value="ADD" />
						</div>
						<div class="col-md-4"></div>
					</div>
				</form>
				<br> <a href="adminhome.jsp"> BACK </a>
			</div>
		</div>

</div>
		

</body>

</html>

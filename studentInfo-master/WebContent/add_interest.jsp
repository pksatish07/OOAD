<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Add Interest</title>
</head>
<body background="images/background.jpg">
	
	
	<br/><br/><br/>
	
	<div class="col-md-6 col-md-offset-3">
		<div class="well">

		
			
<br/>
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-4">
					<h2>Add Interest</h2>
				</div>
				<div class="col-md-4"></div>
			</div>
			<br>


			<form action="addInterest" method="post"
				enctype="multipart/form-data">

				<div class="row">

					<div class="col-md-4"></div>
					<div class="col-md-4">
						<div class="form-group">
							<label for="name">Name:</label> <input type="text"
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
							<label for="comment">Details:</label>
							<textarea class="form-control" rows="5" id="details"
								name="details"></textarea>
						</div>
					</div>
					<div class="col-md-4"></div>
				</div>

				<div class="row">
					<div class="col-md-4"></div>
					<div class="col-md-4">
						<input type="submit" value="ADD" />
					</div>
					<div class="col-md-4"></div>

				</div>


				<div class="row">
					<div class="col-md-4"></div>
					<div class="col-md-4"></div>
					<div class="col-md-4">
						<a href="adminhome.jsp"> BACK </a>
					</div>

				</div>
			</form>
		</div>
	</div>

</body>
</html>
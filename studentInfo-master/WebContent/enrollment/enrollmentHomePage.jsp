<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Subject Enrollment</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="bootstrap/js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>

</head>
</head>
<body>
	<form action="UpdateEnrollment" method="post">
		<div class="well" align="center">
			<p class="lead">Subject Enrollment</p>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-7 col-sm-offset-1">
					<div class="row">
						<p class="lead">Subjects Already Enrolled</p>
					</div>
					<div class="row">
						<table
							class="table table-responsive table-hover table-striped table-condensed">
							<thead>
								<tr>
									<td><b>Course Code</b></td>
									<td><b>Course Name</b></td>
									<td><b>Credits</b></td>
									<td><b>Remove</b></td>
								</tr>
							</thead>
							<s:iterator value = "subjectsToDelete" var = "enrolled">
							<tr>
									<td><s:property value = "#enrolled.subjectCode"></s:property></td>
									<td><s:property value = "#enrolled.subjectName"></s:property></td>
									<td><s:property value = "#enrolled.credits"></s:property></td>
									<td><input type="checkbox" value= <s:property value = "#enrolled.subjectCode"></s:property>
										name="toDeleteCheckbox"></td>
							</tr>
							</s:iterator>
						</table>
					</div>
					<br> <br>
					<div class="row">
						<p class="lead">Subjects you can enroll</p>
						<table
							class="table table-responsive table-hover table-striped table-condensed">
							<thead>
								<tr>
									<td><b>Course Code</b></td>
									<td><b>Course Name</b></td>
									<td><b>Credits</b></td>
									<td><b>Add</b></td>
								</tr>
							</thead>
							<s:iterator value = "subjectsToAdd" var = "notEnrolled">
							<tr>
									<td><s:property value = "#notEnrolled.subjectCode"></s:property></td>
									<td><s:property value = "#notEnrolled.subjectName"></s:property></td>
									<td><s:property value = "#notEnrolled.credits"></s:property></td>
									<td><input type="checkbox" value= <s:property value = "#notEnrolled.subjectCode"></s:property>
										name="toAddCheckbox"></td>
							</tr>
							</s:iterator>
						</table>
					</div>
				</div>
			</div>
			<br>
			<div class="row" align="center">
				<input type="submit" class="btn btn-warning btn-lg" value="Update"
					onclick="validate(this)">
			</div>
			
		</div>
	</form>
</body>

<%-- <script type="text/javascript">
	function submitForm() {
		document.getElementById("subjectsForm").submit();
	}

	
</script> --%>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap.css">

<script src="bootstrap/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Assign grades</title>

</head>
<body>
<div class="container-fluid">
    <div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-6">
				<h1>
					<font color="black">Student Information center</font>
				</h1>
			</div>
			<div class="col-md-2"></div>
		</div>
		<br>
	<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<h2><center>Assign grades</h2>
			</div>
			<div class="col-md-4"></div>
		</div>
		<br> 
	
	<div class="col-md-8 col-md-offset-2">
			<div class="well" >
			<div style="text-align: left">
		<form action="updateGradesAction" method="post" name="studentsGrade"  enctype="multipart/form-data">
			     <br>

					<div class="row">

						<div class="col-md-4"></div>
						<div class="col-md-4">
						<label >Student Name</label>
					<s:select id="studentDisplayChoice" name="studentDisplayChoice"
							list="studentList"
							onchange="onStudentChange()"></s:select>
							</div>
						<div class="col-md-4"></div>
					</div>
							<br>
					<div class="row">

						<div class="col-md-4"></div>
						<div class="col-md-4">
						<label >Course Name</label>
						<s:select id="courseDisplayChoice" name="courseDisplayChoice" list="currentSemCurrentList">
						 
	
						</s:select></div>
						<div class="col-md-4"></div>
					</div>
					<br>		
				<div class="row">

						<div class="col-md-4"></div>
						<div class="col-md-4">
						<label >Grade</label>
					<s:select id="gradeDisplayChoice" name="gradeDisplayChoice" list="gradeList" ></s:select></div>
						<div class="col-md-4"></div>
					</div>
							<br>
					
							<div class="row">
						<div class="col-md-4"></div>
						<div class="col-md-4">
							<input type="submit" class="btn btn-primary" value="Assign" />
						</div>
						
						<div class="col-md-4">
		<a href="adminhome.jsp" class = "btn btn-primary">BACK</a>
		</div>
						
					</div>

		</form>
	</div>
	</div>
	</div>
	</div>
	</div>

	<script type="text/javascript">
		function onStudentChange() {
			document.studentsGrade.action = 'assignGradesAction.action';
			document.studentsGrade.submit();
		}
	</script>

</body>
</html>
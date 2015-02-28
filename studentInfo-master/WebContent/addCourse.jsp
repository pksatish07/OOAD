<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap.css">

<title>Add Course</title>

</head>
<body>
<div class ="container">
	<br>
	<br>

	<div>
		<div align="center">
			<h3>Add Course</h3>
		</div>
	</div>
	<form action="addSubjectsAction" method="post"
		onsubmit="return validateForm(this)">
		<div class="well">
			<div class="row">
				<div class="col-md-3 col-md-offset-3">
					<label class="pull-right">Course Code:<span
						class="glyphicon glyphicon-asterisk"></span></label>
				</div>
				<div class="col-md-3">
					<input type="text" class="form-control" name="code"
						placeholder="Course Code" required="required">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-3 col-md-offset-3">
					<label class="pull-right">Course Name:<span
						class="glyphicon glyphicon-asterisk"></span></label>
				</div>
				<div class="col-md-3">
					<input type="text" class="form-control" name="name"
						placeholder="Course Name" required="required">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-3 col-md-offset-3">
					<label class="pull-right">Choose Semester:<span
						class="glyphicon glyphicon-asterisk"></span></label>
				</div>
				<div class="col-md-3">
					<select name="semester">
						<option>1</option>
						<option>2</option>
						<option>3</option>
						<option>4</option>
					</select>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-3 col-md-offset-3">
					<label class="pull-right">Credits:<span
						class="glyphicon glyphicon-asterisk"></span></label>
				</div>
				<div class="col-md-3">
					<input type="text" class="form-control" name="credit"
						placeholder="No of Credits" required="required">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-3 col-md-offset-3">
					<label class="pull-right">Choose Faculty:<span
						class="glyphicon glyphicon-asterisk"></span></label>
				</div>
				<div class="col-md-3 pull-left">
					<s:select name="faculty" list="facultyList" label="Faculty "
						cssStyle="width:230px;"></s:select>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-3 col-md-offset-3">
					<label class="pull-right">Last Date for Enrollment:<span
						class="glyphicon glyphicon-asterisk"></span></label>
				</div>
				<div class="col-md-3">
					<input type="date" class="form-control" name="lastDate"
						placeholder="mm/dd/yyyy" required="required">
				</div>
			</div>
			<br> <br>
			<div class="row">
				<div class="col-md-2 col-md-offset-2">
					<a href="adminhome.jsp" class="btn btn-primary">Back</a>
				</div>
				<div class="col-md-2 col-md-offset-2">
					<div class="col-md-6 col-md-offset-3">
						<input type="submit" class="btn btn-warning btn-lg"
							value="Add Course">
					</div>
				</div>
				<div class="hidden">
					<input type="text" name="faculty" id="facultyname"> <input
						type="text" name="semester" id="semesterNumber">
				</div>
			</div>

		</div>

	</form>
	<script type="text/javascript">
		function validateForm(form) {
			var doc = form.elements;
			if (doc['code'].value == '') {
				alert('Course Code cannot be empty');
				doc['code'].focus();
				return false;
			}
			if (doc['name'].value == '') {
				alert('Course Name cannot be empty');
				doc['name'].focus();
				return false;
			}
			if (doc['credit'].value == '') {
				alert('Credits cannot be empty');
				doc['credi'].focus();
				return false;
			}
			if (doc['lastDate'].value == '') {
				alert('Last Date cannot be empty');
				doc['lastDate'].focus();
				return false;
			}
			return true;
		}
	</script>

	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery-ui.min.js"></script>
	<script type="text/javascript">
		$(function() {
			StartDate = new Date("January 1, 1900");
			EndDate = new Date("March 21, 2014");
			$("#datepicker").datepicker({
				minDate : StartDate,
				defaultDate : StartDate
			});

		});
	</script>
</div>
</body>

<link type="text/css"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.9.2/themes/ui-darkness/jquery-ui.css"
	rel="stylesheet">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>

</html>
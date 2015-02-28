<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title><s:text name="Student Information System" /></title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="bootstrap/js/bootstrap.min.js"></script>
<!--  <link rel="stylesheet" href="mystyle.css" type="text/css" />-->
</head>
<body>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h1>My Profile</h1>

		</div>

		<div class="panel-body">

			<div class="row">

				<label class="control-label col-md-2" for="RollNumber"><font
					size="3">RollNumber:</font></label>
				<div class="col-sm-6 pull-left">
					<s:property value="rollno" />
				</div>
				<label class="control-label" for="Interests"><font size="3">Interests:</font></label>
				<div class="col-md-3 col-md-offset-9">
					<ol>
						<s:iterator value="defaultInterests">
							<li><s:property /></li>
						</s:iterator>
					</ol>
				</div>
			</div>
			<br><br>
			<div class="row">
				<label class=" col-md-6" for="Name"><font
					size="3">Name:<s:property value="name" /></font></label> <label
					class="col-md-3 col-md-offset-2" for="semester"><font
					size="3">Semester:<s:property value="semester" /></font></label><br>
			</div>

			<br><br>


			<s:form action="edit">
				<input type="submit" value="Edit My Profile"
					style="width: 200px; height: 60px" class="btn btn-primary">

			</s:form>
		</div>
	</div>
	</div>
</body>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title><s:text name="Student Information System" /></title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

	<div class="panel panel-primary">
		<div class="panel-heading">
			<h1>Edit Profile</h1>
		</div>

		<div class="panel-body">
			<form action="submit" method="post" enctype="multipart/form-data"
				onsubmit="return onTypingPassword(this)">
				<label class="control-label col-md-2" for="RollNumber"><font
					size="3">RollNumber:</font></label>

				<div class="col-md-2">
					 <%-- <input type="text"
						value="<s:property  name = "rollno" ></s:property>" /> --%> 
						<s:textfield name="rollno" readonly="true"></s:textfield>
				</div>
				<div class="col-md-1 col-md-offset-1"></div>
				<label class="control-label col-md-2 pull-left" for="Password"><font
					size="3">Password:</font></label>

				<div class="col-md-2 pull-left">
					<s:password name="password" showPassword="true">
					</s:password>
				</div>
				<div class="col-md-2"></div>
				<br> <br> <br> <label class="control-label col-md-2"
					for="Name"><font size="3">Name:</font></label>

				<div class="col-md-2">
					<s:textfield name="name"></s:textfield>
				</div>
				<label class="control-label col-md-2 col-md-offset-2" for="reenterPassword"><font
					size="3">Re-enter Password:</font></label>
				<div class="col-md-2 pull-left">
					<s:password name="reenterpassword" showPassword="true">
					</s:password>
				</div>
				<br><br><br>
				<div class = "row">
					<div class="col-md-4">
						<s:file name="fileUpload" />
					</div>
				</div>
				<div class = "row">
				<br><br>
				 <label class="control-label col-md-2"
					for="interests"><font size="3">Interests:</font></label>
				<div class="col-md-11">

					<s:checkboxlist list="interests" name="interests"
						value="defaultInterests"></s:checkboxlist>
				</div>
				</div>
				
				
				
				<br> <br> <br>
				
				<label class="col-md-2" for="semester"><font
					size="3">Semester:</font></label>

				<div class="col-md-2 pull-left">
					<s:textfield name="semester"></s:textfield>
				</div>
				<div class="col-md-1"></div>
				<br> <br> <br>
				<div class="col-md-2"></div>
				<div class="col-md-2">
					<input type="submit" value="SUBMIT"
						class="btn btn-primary btn-lg">
				</div>
			</form>
		</div>
	</div>
	<s:actionerror />

	<script type="text/javascript">
			function onTypingPassword(form) {
				var e = form.elements;
				if (e['password'].value != e['repassword'].value) {
					alert('Your passwords do not match. Please type more carefully.');
					return false;
				}
				return true;
			}
		</script>
	</td>
	</tr>
	</table>
</body>
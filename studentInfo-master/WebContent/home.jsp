<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Student Information System</title>
</head>
<body background="./layout/resources/images/iiitb.jpg">
	<h1 align="middle">STUDENT INFORMATION SYSTEM</h1>
	<div align="center">
	<s:form action="loginAction" autocomplete="off">

		<s:textfield key="username" label="Username" />
		<s:password key="password" label="Password" />

		<s:submit />
	</s:form>
	</div>
</body>
</html> --%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Login Page</title>
<style type="text/css">
#idef {
	color: white;
}

.errorDiv {
	background-color: gray;
	border: 0.5px red;
	width: 210px;
	margin-bottom: 4px;
}
</style>
<script type="text/javascript">
	function validate() {
		var name = document.getElementById("name");
		var password = document.getElementById("pwd");
		var valid = true;
		if (name.value.length <= 0 && password.value.length <= 0) {
			alert("Enter User Name & Password!");
			valid = false;
		} else if (name.value.length <= 0) {
			alert("User Name cannot be empty!");
			valid = false;
		} else if (password.value.length <= 0) {
			alert("Password cannot be empty!");
			valid = false;
		}

		/* else {
			if (isNaN(c) || isNaN(d)) {
				alert("Enter a number");
				valid = false;
			}
		} */
		return valid;
	};
</script>
</head>
<body>
	<div class="container-fluid" style="background-color: #D3D3D3">
		<!-- #E0E6F9 -->
		<div class="row" style="background-color: #999999;">
			<!-- #003D7A -->
			<div class="col-md-7">
				<br>
				<h1 id="idef">
					<strong>Student Information System - IIITB</strong>
				</h1>
			</div>
			<div class="col-md-5">
				<br>
				<form action=loginAction method="post" class="form-inline"
					role="form" onsubmit="return validate();">
					<div class="form-group">
						<label id="idef" for="user name">User Name</label><br> <input
							type="text" class="form-control" name="username"
							placeholder="Enter user name" id="name"><br>
							<br>
							<br>
					</div>
					<div class="form-group">
						<label id="idef" for="pwd">Password</label> <br> <input
							type="password" class="form-control" id="pwd"
							placeholder="Enter password" name="password"><br> <a
							id="idef" href="<s:url action="forgotPasswordRedirect"/>"><h6>forgot
								password?</h6></a>
					</div>
					<div class="form-group">
						<br>
						<button type="submit" class="btn btn-primary"
							style="background-color: #999999;">Log In</button>
							<br>
							<br>
							<br>
						<!-- #003D7A -->
					</div>
					<s:if test="hasActionErrors()">
						<div class="errorDiv">
							<s:actionerror />
						</div>
					</s:if>
				</form>

			</div>
		</div>
		<div class="row">
			<div class="col-md-7" style="overflow: hidden;">
				<div class="container">
					<img src="images/iiitb.png" class="img-responsive" alt="IIITB"
						width="820">
				</div>
			</div>
		</div>
	</div>

</body>
</html>
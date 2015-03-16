<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Password Problem</title>
</head>
<body style="background-color: #D3D3D3">
	<div class="container-fluid">
		<div class="row">
			<h3 class="col-sm-4 col-sm-offset-4">Enter your Details</h3>
			<form action="forgotPassword" method="post" class="form-horizontal"
				role="form">
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-4">
						<input type="text" class="form-control" name="username"
							placeholder="Enter user name" id="username">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-4 col-sm-4">
						<input type="email" class="form-control" name="emailId"
							placeholder="Enter Email ID" id="emailId">
					</div>
				</div>
				<s:if test="hasActionErrors()">
					<div class="errorDiv col-sm-offset-4">
						<s:actionerror />
					</div>
				</s:if>
				<div class="form-group">
					<div class="col-sm-offset-5 col-sm-4">
						<button type="submit" class="btn btn-primary">Reset
							Password</button>
					</div>
				</div>
			</form>
		</div>
		<div class="row col-sm-offset-4">
			<h4>New password will be sent to your email address registered with us</h4>
		</div>
	</div>
</body>
</html>
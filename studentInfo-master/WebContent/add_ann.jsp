<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Announcements</title>
</head>
<body style="background-color: #D3D3D3">
	<div class="container-fluid">
		<div class="row" style="background-color: #999999;">
			<h2 align="middle">STUDENT INFORMATION SYSTEM</h2>
			<br>
			<h2 align="middle">Add Announcements</h2>
		</div>
		<br>
		<s:form action="addann">
			<s:textfield key="name" label="name" />
			<s:textarea key="details" name="details" rows="5" cols="50" />
			<s:select key="interest" list="li"></s:select>
			<s:submit label="Add" />
		</s:form>
		<br> <a href="adminhome.jsp"> BACK </a>
	</div>

</body>
</html>
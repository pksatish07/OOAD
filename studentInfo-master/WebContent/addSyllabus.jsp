<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Syllabus</title>
<a href="adminhome.jsp" class="btn btn-primary">Back</a>
<script
		src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
</head>
<body>
		<nav class="nav navbar-inverse">
		<center><label style="color:white"><h1>Add Syllabus</h1></label></center>
		</nav>
		<div class="panel panel-default" style="background:grey">
		<form action="addSyllabusAction" method="post" name=termDDL>
			<br />
			<div class="row">
			<div class ="col-md-3 col-md-offset-1 pull-left">
					<s:select name="subjectName" list="subjectCodeList" id="termDisplayChoice" onchange="onTermChange()" label="Course Name "></s:select>
			</div>
			</div>
			<br />
			<div class="row">
			<div class ="col-md-3 col-md-offset-1 pull-left">
			<label style="color:white"><h6><b>Write Syllabus here:</b></h6></label></div></div>
			<div class="row">
			<div class ="col-md-3 col-md-offset-1 pull-left">
					<textarea name="topic" cols="100" rows="10" ><s:property value="currentSyllabus" /></textarea>
			</div>
			</div>
			<br />
			<div class="row">
			<div class ="col-md-3 col-md-offset-1 pull-left">
				<s:submit align="left" value="Submit"/>
				</div></div>
			
		</form>
		</div>
		<script type="text/javascript">
		function onTermChange() {
			//document.getElementById("courseDisplayChoice").selectedIndex = 0;
			document.termDDL.action = 'syllabus.action';
			document.termDDL.submit();
		}
		</script> 
</body>
</html>
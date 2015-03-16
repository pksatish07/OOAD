<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Syllabus Page</title>
</head>
<body>
	<a href="subjectsAction" style="color:white">Back</a>
	<div style="text-align:center">
		<span><h1>Syllabus of <s:property value="subjectCode"/></h1></span>
		<div class="row">
				<s:property value="syllabusInfoList"></s:property>
		</div>
	</div>
</body>
</html>

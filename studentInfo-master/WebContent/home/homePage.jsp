<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		<div align="left" style="margin-left: 10px;">
			<h2 style="color:#412066;"><s:property value="user.name" />&apos;s Home</h2>
			<br />
			Welcome to Student Information System.<br /><br />
			<b>Tips:</b><br />
			<ul>
				<li>Click on your photo to view your profile.</li>
				<li>Type the roll number of the student you wish to search for in the search textbox in the menu.</li>
				<li>Use the five hyperlinks (Home, Grades, Subjects, Enrollment and My Friends) to navigate to the respective pages.</li>
				<li>Announcements matching your interests are displayed below on the left. Hover the mouse pointer over the <br />section to stop auto-scrolling.</li>
				<li>The news are displayed below on the right. Hover the mouse pointer over the section to stop auto-scrolling.</li>
			</ul>
		</div>
		<br />
	</div>
</body>
</html>

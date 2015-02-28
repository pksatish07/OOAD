<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Grades</title>
</head>
<body>
	<h2 style="color:#412066;">Grades</h2>

			<form action="grades.action" name=termDDL method=post>

				<!-- The semester dropdown list -->
				<s:select id="termDisplayChoice" name="termDisplayChoice" onchange="onTermChange()"
					list="termList" label="Semester"></s:select>

				<!-- The subject dropdown list -->
				<s:select id ="courseDisplayChoice" name="courseDisplayChoice" onchange="onCourseChange()"
					list="courseList" label="Subject"></s:select>

			<!--  The grades table -->

				<table border="1">
					<thead>
						<tr>
							<td><b><center>Subject Code</center></b></td>
							<td><b><center>Subject</center></b></td>
							<td><b><center>Grade</center></b></td>
							<td><b><center>Result</center></b></td>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="resultList" status="subject">
							<tr>
								<td><center><s:property value="subjectCode" /></center></td>
								<td><center><s:property value="subjectName" /></center></td>
								<td><center><s:property value="grade" /></center></td>
								<td><center><s:property value="result" /></center></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>

		</form>

	<script type="text/javascript">
		function onTermChange() {
			document.getElementById("courseDisplayChoice").selectedIndex = 0;
			document.termDDL.action = 'grades.action';
			document.termDDL.submit();
		}
		function onCourseChange() {			
			document.termDDL.action = 'grades.action';
			document.termDDL.submit();
		}
	</script>



</body>
</html>
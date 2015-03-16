<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Course</title>
<a href="adminhome.jsp">Back</a>

</head>
<body>
	<h1>Edit Semester</h1>
	<div style="text-align: left">
		<form action="Updatesemester" method="post" name="studentsSemester">
			<table width="50%">
				<tr>
					<td><s:select id="studentDisplayChoice" name="studentDisplayChoice"
							list="studentList" label="Student Name " cssStyle="width:230px;"
							onchange="onStudentChange()"></s:select></td>
					
				</tr>
				<tr>
					<td><label>Semester :</label>
					</td><td>
					<select id="semesterdisplaychoice" name="semesterdisplaychoice" label="Semester"> <option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
							<option value="4">4</option></select></td>
				<tr>
					<td><s:submit align="left" value="Assign" /></td>
				</tr>
			</table>

		</form>
	</div>

	<script type="text/javascript">
		function onStudentChange() {
			document.studentsGrade.action = 'editsemesteraction.action';
			document.studentsGrade.submit();
		}
	</script>

</body>
</html>
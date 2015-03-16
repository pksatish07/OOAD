<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Subjects Page</title>
</head>
<body>
	<div>
		<h2 style="color: #412066;">Subjects</h2>
		<div style="width: 60%; float: left;">
			<form action="subjectsAction" method="post" id="subjectsForm">
				<div style="text-align: center">
					<!-- <s:select list="subjectDisplayList" name="subjectDisplayChoice"
						onchange="javascript:submitForm()"></s:select>-->
				</div>
				<div>
					<table border="1" style="margin: 0px auto">
						<thead>
							<tr>
								<td><b><center>Subject Code</center></b></td>
								<td><b><center>Subject</b>
									</center></td>
								<td><b><center>Faculty Name</center></b></td>
								<td><b><center>Semester</center></b></td>
								<td><b><center>Enrolled</center></b></td>
								<td><b><center>Grade</center></b></td>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="subjectInfoList" status="subject">
								<tr>
									<td><s:url action="syllabusAction" var="syllabusURL">
											<s:param name="subjectCode" value="subjectCode" />
										</s:url>
										<center>
											<s:a href="%{syllabusURL}" style="color:white">
												<s:property value="subjectCode" />
											</s:a>
										</center></td>
									<td><center>
											<s:property value="subjectName" />
										</center></td>
									<td><center>
											<s:property value="facultyName" />
										</center></td>
									<td><center>
											<s:property value="semester" />
										</center></td>
									<td><center>
											<s:property value="enrolled" />
										</center></td>
									<td><center>
											<s:property value="grade" />
										</center></td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</form>
		</div>
		<div style="width: 20%; float: left;"></div>
	</div>
</body>

<script type="text/javascript">
	function submitForm() {
		document.getElementById("subjectsForm").submit();
	}
</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!--Add struts library  -->
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="bootstrap/js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<title>Admin Home page</title>
</head>
<body>

	<div class="panel panel-default">
		<div class="panel-body" style="background: #D3D3D3">
			<div class="alert alert-info" style="background: #999999">
				<h1 align="center" style="color: white;">Admin Home</h1>

				<!-- Added username -->

				<h5 align="right">
					<s:property value="#session.user.name" />
					|<a style="color: WHITE;" href="logout"> Logout</a>
				</h5>


				<!-- <h5 align="right"><small><s:property value="admin" /></small> <a href="logout" style="color:white;">Logout</a></h5> -->
			</div>
			<h2 align="center">Contents</h2>
			<br>

			<div class="panel panel-default col-lg-2 col-lg-offset-1"
				style="background: #999999;">
				<div class="panel-body">
					<a href="AdminEditImage.jsp"><center>
							<img class="img-thumbnail" width="200px"
								src='adminimageAction?userId=<s:property value="#session.user.userId" />' />
						</center> </a>
				</div>
			</div>

			<div>
				<div class="col-md-2"></div>

				<div class="col-md-8">
					<div class="btn-group btn-group-justified" role="group"
						aria-label="...">
						<div class="btn-group" role="group">
							<button type="button" class="btn btn-default btn-lg"
								onclick="window.location='add_user.jsp'"
								style="background-color: teal; color: white;">Add User</button>
						</div>
						<div class="btn-group" role="group">
							<button type="button" class="btn btn-default btn-lg"
								onclick="window.location='initSubjectsAction'"
								style="background-color: teal; color: white;">Add
								Course</button>
						</div>
						<div class="btn-group" role="group">
							<button type="button" class="btn btn-default btn-lg"
								onclick="window.location='selectSyllabusAction'"
								style="background-color: teal; color: white;">Add
								Syllabus</button>
						</div>
					</div>

					<div class="btn-group btn-group-justified" role="group"
						aria-label="...">
						<div class="btn-group" role="group">
							<button type="button" class="btn btn-default btn-lg"
								onclick="window.location='assignGradesAction'"
								style="background-color: teal; color: white;">Assign
								Grades</button>
						</div>
						<div class="btn-group" role="group">
							<button type="button" class="btn btn-default btn-lg"
								onclick="window.location='add_interest.jsp'"
								style="background-color: teal; color: white;">Add
								Interest</button>
						</div>
						<div class="btn-group" role="group">
							<button type="button" class="btn btn-default btn-lg"
								onclick="window.location='addAnnouncement'"
								style="background-color: teal; color: white;">Add
								Announcement</button>
						</div>
					</div>

					<div class="btn-group btn-group-justified" role="group"
						aria-label="...">
						<div class="btn-group" role="group">
							<button type="button" class="btn btn-default btn-lg"
								onclick="window.location='add_news.jsp'"
								style="background-color: teal; color: white;">Add News</button>
						</div>
						<div class="btn-group" role="group">
							<button type="button" class="btn btn-default btn-lg"
								onclick="window.location='remove_news.jsp'"
								style="background-color: teal; color: white;">Remove
								News</button>
						</div>
						<div class="btn-group" role="group">
							<button type="button" class="btn btn-default btn-lg"
								onclick="window.location='remove_ann.jsp'"
								style="background-color: teal; color: white;">Remove
								Announcements</button>
						</div>

					</div>


					<div class="btn-group btn-group-justified" role="group"
						aria-label="...">
						<div class="btn-group " role="group">
							<button type="button" class="btn btn-default btn-lg"
								onclick="window.location='resetpwd.jsp'"
								style="background-color: teal; color: white;">Reset
								Password</button>
						</div>

						<div class="btn-group" role="group">
							<button type="button" class="btn btn-default btn-lg"
								onclick="window.location='add_prof.jsp'"
								style="background-color: teal; color: white;">Add
								Professor</button>
						</div>
						<div class="btn-group" role="group">
							<button type="button" class="btn btn-default btn-lg"
								onclick="window.location='delete_prof.jsp'"
								style="background-color: teal; color: white;">Remove
								Professor</button>
						</div>


					</div>

					<div class="btn-group btn-group-justified" role="group"
						aria-label="...">
						<div class="btn-group" role="group">
							<button type="button" class="btn btn-default btn-lg"
								onclick="window.location='Editsemesteraction'"
								style="background-color: teal; color: white;">Edit
								Semester</button>
						</div>
					</div>


				</div>

				<div class="col-md-2"></div>
			</div>




		</div>
	</div>

</body>
</html>
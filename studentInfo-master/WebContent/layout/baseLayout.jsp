<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ page import = "org.iiitb.action.notification.*" %>
<%@ page import = "org.iiitb.model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

			<style type="text/css">
            .scrollable {
                height: 100%;
                overflow: auto;
            }
            .no-overflow {
                overflow: hidden;
            }

            .pad40-top {
                padding-top: 40px;
            }
    </style>
<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Student Zone</title>
</head>
<body background="images/background.jpg">
	<!-- <div class="panel panel-default">
		<div class="panel-body" style="background: #D3D3D3"> -->
			<div class="container">
				<tiles:insertAttribute name="banner" />
 
				<nav class="navbar navbar-inverse">
					<div class="container-fluid">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle collapsed"
								data-toggle="collapse"
								data-target="#bs-example-navbar-collapse-2">
								<span class="sr-only">Toggle navigation</span> <span
									class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>
						</div>

						<div class="collapse navbar-collapse"
							id="bs-example-navbar-collapse-2">
							<form class="navbar-form navbar-left" id = "searchForm" action="friendProfile" method="post">
								<div class="form-group">
									<input class="form-control" name="friendNo" placeholder="Search" type="text">
								</div>
								<a href="javascript:{}" type="button" class="btn btn-default"
									onclick="document.getElementById('searchForm').submit(); return false;"><b>Search</b></a>
							</form>
							<ul class="nav navbar-nav navbar-right">



								<li class="active"><a href="layoutAction" type="button">Home
										<span class="sr-only">(current)</span>
								</a></li>
								<li class="active"><a href="enrollmentAction" type="button">Enrollment
										<span class="sr-only">(current)</span>
								</a></li>
								<li class="active"><a href="grades.action" type="button">Grades
										<span class="sr-only">(current)</span>
								</a></li>
								<li class="active"><a href="subjectsAction" type="button">Syllabus
										<span class="sr-only">(current)</span>
								</a></li>
								<li class="active"><a href="friendsPage" type="button">My
										Friends <span class="sr-only">(current)</span>
								</a></li>
									
									<!-- Changed for Notification functionality -->
									<%  NotificationCount count = new NotificationCount();
											
											
											int displayCount = count.notificationCount(Integer.parseInt(((String)((User)session.getAttribute("user")).getUserId())));
											//System.out.println("displayCount "+displayCount);%>
									
									<li class="active"><a href="notificationAction" type="button">Notification (<%= displayCount %>)
										 <span class="sr-only">(current)</span>
								</a></li>
								
								
							</ul>
						</div>
					</div>
				</nav>
			</div>
			<div class="panel panel-default col-lg-2 col-lg-offset-1" style="background:#999999;">
				<div class="panel-body">
					<a href='view'> <center><img class="img-thumbnail" width="200px"
						src='imageAction?userId=<s:property value="#session.user.userId" />'/></center>
					</a> <br> Last logged on:
					<s:property value="lastLoggedOn" />
				</div>
			</div>
			<div class="panel panel-default col-lg-7 col-lg-offset-1 " style="background:#999999">
				<div class="panel-body">
					<tiles:insertAttribute name="body" />
				
				</div>
			</div>
			<div><label>__________________________________________________________________________________________________________________________________________________________________________________________</label></div>
				<div class="panel panel-warning col-lg-4 col-lg-offset-1" style="background:#999999">
				<div class="panel-heading">News</div>
				<div class="panel-body panel-height scrollable no-overflow">
					<marquee direction="up" onmouseover="this.stop();"
						onmouseout="this.start();">
						<ol>
							<s:iterator value="allNews" status="NewsItem">
								<li><b><s:property value="name" /></b><br /> <small><s:property
											value="details" /></small>
									<hr /></li>
							</s:iterator>
						</ol>
					</marquee>
				</div>
			</div>
				<div class="panel panel-warning col-lg-5 col-lg-offset-1" style="background:#999999">
				<div class="panel-heading">Announcement</div>
				<div class="panel-body panel-height">
					<marquee direction="up" onmouseover="this.stop();"
						onmouseout="this.start();">
						<ol>
							<s:iterator value="announcements" status="announcementsItem">
								<li><b><s:property value="name" /></b><br />
								<small><s:property value="details" /></small>
								<hr /></li>
							</s:iterator>
						</ol>

					</marquee>
				</div>
			</div>
		<!-- </div>
	</div> -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

</body>
</html>
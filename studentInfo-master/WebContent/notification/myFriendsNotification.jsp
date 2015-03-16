<%@page import="sun.text.normalizer.CharTrie.FriendAgent"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MyFriends Page</title>

<script src="js/jquery.js">
	
</script>

<script src="js/main.js"></script>

<script src="js/jquery.bxslider.min.js"></script>
<!-- bxSlider CSS file -->
<link href="css/jquery.bxslider.css" rel="stylesheet" /> 

 <link rel="stylesheet"
	href="bootstrap/css/bootstrap.css">
<script
	src="bootstrap/js/bootstrap.js"></script>
</head>


<body>
<div class="container">	
<h3> Click on image to view profile , Accept/Reject Friend request </h3>
<div class="row">

  <div class="col-sm-7">
	
	<div class="panel panel-default" >
<div class="panel-heading" style="background-color: lightgrey">
      <div class="col-sm-5"></div><b>Friend Requests</b></div>
   

<div class="panel-body" style="background-color: grey">
	
	<ul class="bxslider">
		<s:iterator value="students" var="student">
			<s:url id="friend" action="friendProfile" var="myurl">
				<s:param name="friendNo">
					<s:property value="#student.rollNo" />
				</s:param>

			</s:url>
			
			<li><b><s:property
						value="#student.name" /></b><a href='<s:property value="#myurl"/>'><img width="300" height="200"
					src="imageAction?rollNo=<s:property value="#student.rollNo"/>"
					title='<s:property
						value="#student.name" />' /></a></li>




		</s:iterator>

	</ul>
	</div>
	</div>
	
	</div>
	 <div class="col-sm-3"></div>
</div>
</div>
</body>
</html>

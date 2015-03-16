<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.sql.Connection"
	import="java.sql.PreparedStatement" import="java.sql.SQLException"
	import="java.util.Map" import="org.iiitb.util.ConnectionPool"
	import="java.sql.ResultSet"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="bootstrap/js/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>

<%-- <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script> --%>

<title>Remove Announcements</title>
</head>
<body>


	<div class="panel panel-default">
		<div class="panel-body" style="background: #D3D3D3">
			<div class="alert alert-info" style="background: #999999">
				<h1 align="center" style="color: white;">STUDENT INFORMATION
					SYSTEM</h1>

			</div>
			<h2 align="center">Remove Announcements</h2>

			<div align="center">

				<s:form action="removeann" method="execute">


					<div class="row">

						<div class="col-md-4"></div>
						<div class="col-md-4">
							<div class="form-group">
								<div class="form-horizontal">
									<div class="control-group row-fluid form-inline">


										<div class="btn-group">
											<button type="button" class="btn btn-default btn-lg">Select
												Announcement</button>
											<button type="button"
												class="btn btn-default btn-lg dropdown-toggle"
												data-toggle="dropdown">
												<span class="caret"></span>

											</button>
											<ul class="dropdown-menu" role="menu">
												<%
													Connection conn = ConnectionPool.getConnection();
														String[] names = null;
														PreparedStatement ps = null;
														ResultSet rs = null;

														int count = 0;

														try {
															ps = conn
																	.prepareStatement("Select name from announcement;");

															rs = ps.executeQuery();
															ResultSet size = conn.prepareStatement(
																	"Select count(*) from announcement;")
																	.executeQuery();

															if (size.next())
																names = new String[size.getInt(1)];

															while (rs.next()) {
																names[count] = rs.getString(1);
																//System.out.println(names[count]);
																count++;
															}
														}

														catch (SQLException e) {
															e.printStackTrace();
														} finally {

															try {
																if (rs != null)
																	rs.close();
																if (ps != null)

																	ps.close();
															} catch (SQLException e) {
																e.printStackTrace();
															}

															ConnectionPool.freeConnection(conn);
														}
														int i = 0;
														for (i = 0; i < count; i++) {
												%>
												<li><a href="#"><%=names[i]%></a></li>
												<%
													}
												%>


											</ul>
										</div>
										<div class="hidden">
											<input type="text" name="name" id="name">

										</div>
										<input type="submit" class="btn btn-default btn-lg"
											value="Delete">
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-4"></div>
					</div>
					<!--<s:textfield key="name" label="name" />
		
		<s:submit />-->
				</s:form>


			</div>

			<div align="center">
				<s:if test="hasActionErrors()">
					<div class="errors"
						style="background-color: #FFCCCC; border: 1px solid #CC0000; width: 550px;">
						<s:actionerror />
					</div>
				</s:if>

				<s:if test="hasActionMessages()">
					<div class="welcome"
						style="background-color: #DDFFDD; border: 1px solid #009900; width: 550px;">
						<s:actionmessage />
					</div>
				</s:if>
			</div>
			<div align="right">
				<a href="adminhome.jsp"> BACK </a>

			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function() {

			$(".dropdown-menu li a").click(function() {

				$(".btn:first-child").text($(this).text());
				$(".btn:first-child").val($(this).text());

				var selected = $(this).text();

				document.getElementById("name").text = selected;
				document.getElementById("name").value = selected;
				document.getElementById("name").value = selected;

				//  document.write(selected);
			});

		});
	
		
	</script>


</body>
</html>
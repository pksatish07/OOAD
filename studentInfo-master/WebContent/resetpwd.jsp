<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap.css">

<script src="bootstrap/js/bootstrap.min.js"></script>


<title>Reset Password</title>
</head>
<body>

	<div class="panel panel-default">
		<div class="panel-body" style="background: #D3D3D3">
			<div class="alert alert-info" style="background: #999999">
				<h1 align="center" style="color: white;">Reset Password</h1>


				<div class="well">
					<form name="reset" action="resetpwd" method="post"
						onsubmit="return validate()">

						<div class="row">

							<div class="col-md-4"></div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="name">Old Password:</label> <input type="password"
										class="form-control" name="oldpwd" id="oldpwd">
								</div>
							</div>
							<div class="col-md-4"></div>
						</div>

						<div class="row">

							<div class="col-md-4"></div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="name">New Password:</label> <input type="password"
										class="form-control" name="newpwd" id="newpwd">
								</div>
							</div>
							<div class="col-md-4"></div>
						</div>

						<div class="row">

							<div class="col-md-4"></div>
							<div class="col-md-4">
								<div class="form-group">
									<label for="name">ReEnter New Password:</label> <input
										type="password" class="form-control" name="reenterpwd"
										id="reenterpwd">
								</div>
							</div>
							<div class="col-md-4"></div>
						</div>



						<div class="row">
							<div class="col-md-2"></div>
							<div class="col-md-2 col-md-offset-2">
								<a href="adminhome.jsp" class="btn btn-primary">Back</a>
							</div>

							<div class="col-md-2">
								<input type="submit" class="btn btn-primary" value="Submit" />
							</div>
							<div class="col-md-4"></div>
						</div>


					</form>

				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">

function validate() {

	var opass = document.reset.oldpwd.value;
	var npass = document.reset.newpwd.value;
	var repass = document.reset.reenterpwd.value;

	if(opass == ""){
		alert("Enter Old Password!");
        return false;
	}

	if(npass == ""){
		alert("Enter new Password!");
        return false;
	}

	if(repass == ""){
		alert("ReEnter new Password!");
        return false;
	}

	if(npass != repass){
		alert("Passwords do not match!");
        return false;
	}
	
	
}

</script>

</body>
</html>
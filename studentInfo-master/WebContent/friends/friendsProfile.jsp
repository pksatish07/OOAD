<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MyFriends Page</title>
</head>
<body>



	<table cellpadding="30">


		<tr>
			<td rowspan="3"><img width="120px"
				src="imageAction?userId=<s:property value="%{friendProfile.studentId}" />" />

			</td>
			<td></td>
			<td></td>
			<td><b>Roll Number</b>
				<hr> <s:property value="%{friendProfile.rollNo}" /></td>
			<td align="center"><b>DOB</b>
				<hr> <s:property value="%{friendProfile.dob}" /></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td><b>Name</b>
				<hr> <s:property value="%{friendProfile.name}" /></td>
			<td></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td><s:form action="updateFriend">
					<s:set name="checkFriend" value="isFriend" />

					<s:if test="%{#checkFriend=='friend'}">
						<td><input type="checkbox" align="top" border="1"
							checked="checked" disabled="disabled" />Friend</td>
						<td><input type="button" value="Add Friend"
							disabled="disabled"></td>
					</s:if>
					<s:elseif test="%{#checkFriend=='not_a_friend'}">

						<td><s:checkbox name="checkMe" fieldValue="true"
								label="Friend"  disabled="true"/> 
						<s:hidden key="friendNo" value="%{friendProfile.rollNo}"></s:hidden>
						<td><s:submit label="Add Friend" value="Add Friend" /></td>
					</s:elseif></td>
			</s:form>

		</tr>
	</table>
</body>
</html>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form:form method="post" action="/home" 
		commandName="register">
		<table>
			<tr>
				<td><form:label path="fname">Firstname</form:label></td>
				<td><form:input path='fname' /></td>
			</tr>
			<tr>
				<td></td>
				<td><form:errors path="fname" /></td>
			</tr>
			<tr>
				<td><form:label path="lname">Lastname</form:label></td>
				<td><form:input path='lname' /></td>
			</tr>
			<tr>
				<td></td>
				<td><form:errors path="lname" /></td>
			</tr>

			<tr>
				<td><form:label path="mname">Middlename</form:label></td>
				<td><form:input path='mname' /></td>
			</tr>
			<tr>
				<td></td>
				<td><form:errors path="mname" /></td>
			</tr>

			<tr>
				<td><form:label path="dateOfBirth">Date of Birth</form:label></td>
				<td><form:input type='date' path='dateOfBirth' /></td>
			</tr>
			<tr>
				<td></td>
				<td><form:errors path="dateOfBirth" /></td>
			</tr>

			<tr>
				<td><input type="submit" value="Submit" /></td>
				<td><input type='reset' value='reset' /></td>
			</tr>
		</table>
	</form:form>

</body>
</html>
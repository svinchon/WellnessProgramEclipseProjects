<%@ page
	language="java"
	contentType="text/html;
	charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="java.text.SimpleDateFormat,java.util.Locale,java.util.Date,java.lang.String"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%!
	String generateTimeStamp() {
		//SimpleDateFormat DateFormat = new SimpleDateFormat("yyyy-MM-DD@HH-mm-ss-SSS", Locale.US);
		SimpleDateFormat DateFormat = new SimpleDateFormat("DDHmsSSS", Locale.US);
		Date d = new Date();
		return DateFormat.format(d);
	}
%>
<%
String ts=generateTimeStamp();
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Wellness Program Registration</title>
<style type="text/css">
body {
	background-image: url(./xcpbg_pattern.png);
	font-size: 12px;
	font-family: arial;
}
td, input, select {
	font-size: 12px;
	width: 180px;
}
table {
	background-color: white;
	border-color: black;
	border-width: 1px;
	border-style:solid;
}
form {
}
.mydiv {
	width: 300px;
	margin-left: auto;
	margin-right: auto;
	//border-color: black;
	//border-width: 1px;
	//border-style:solid;
	height: 100%;
    display: table;
}
.mydivinner {
	display: table-cell;
	//border-color: red;
	//border-width: 1px;
	//border-style:solid;
	vertical-align: middle;
}     
.myheader1 {
	background-color: black;
	font-size: 18px;
	color:  #40bfff;
	height: 50px;
}
.myheader2 {
	background-color: #40bfff;
	height: 5px;
	border-top:0.2px solid white;
}
</style>
</head>
<body>
<div class="mydiv">
<div class="mydivinner">
<form name="test" action="ProcessFormData">
<table>
	<tbody>
		<tr>
			<td colspan="2" align="center"><img width="300" height="200" src="WellnessProgramRegistrationCouple.png"/></td>
		</tr>
		<tr>
			<td class="myheader1" colspan="2" align="center">
				It's time to take care of yourself!
			</td>
		</tr>
		<tr>
			<td class="myheader2" colspan="2" align="center">
			</td>
		</tr>
		<tr>
			<td>Badge Number:</td>
			<td>
				<input type="text" name="badge_number" value="<%= ts%>">
			</td>
		</tr>
		<tr>
			<td>First Name:</td>
			<td>
				<input type="text" name="first_name" value="John">
			</td>
		</tr>
		<tr>
			<td>Last Name:</td>
			<td>
				<input type="text" name="last_name" value="Doe">
			</td>
		</tr>
		<tr>
			<td>User Name:</td>
			<td>
				<input type="text" name="username" value="jdoe<%= ts%>">
			</td>
		</tr>
		<tr>
			<td>Password:</td>
			<td>
				<input type="text" name="password" value="Pa55word">
			</td>
		</tr>
		<tr>
			<td>Birth Date:</td>
			<td>
				<input type="text" name="birth_date" value="1971-12-14">
			</td>
		</tr>
		<tr>
			<td>Weight (kg):</td>
			<td>
				<input type="text" name="weight" value="70">
			</td>
		</tr>
		<tr>
			<td>Sport Activity:</td>
			<td>
				<select name="iSportActivity">
					<option value="1">High</option>
					<option value="2">Medium</option>
					<option value="3">Low</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>Gender:</td>
			<td>
				<select name="gender">
					<option value="male">Male</option>
					<option value="female">Female</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>email:</td>
			<td>
				<input type="text" name="email" value="john.doe<%= ts%>@gmail.com"></td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<input type="submit" value="Wellness Program Registration">
			</td>
		</tr>
	</tbody>
</table>
</form>
</div>
</div>
</body>
</html>
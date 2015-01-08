<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Wellness Program Registration</title>
<style type="text/css">
body {
	background-image: url(./xcpbg_pattern.png);
	font-size: 12px;
	font-family: arial;
}
td, input, select {
	font-size: 12px;
	width: 250x;
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
<form>
<table>
	<tbody>
		<tr>
			<td align="center">
				Thank you for your request.
				It is being processed under the reference <%=request.getParameter("WorkflowId")%>.
				You are going to receive an email from us very soon.
				<input Type="button" VALUE="Back to Wellness Program Registration" onClick="history.go(-1);return true;">
			</td>
		</tr>
	</tbody>
</table>
</form>
</div>
</div>
</body>
</html>
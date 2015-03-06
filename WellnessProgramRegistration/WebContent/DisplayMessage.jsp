<%@ page
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
%>
<html>
<style type="text/css">
body {
	background: 
	linear-gradient(
		rgba(0, 0, 0, .9),
		rgba(0, 0, 0, 0.1)
	),
	url("http://xcp:8080/WellnessProgramRegistration/running-feet.jpg");
	background-size: cover;
	font-family: 'Source Sans Pro', sans-serif;
}
header {
  position: absolute;
	top: 50%;
	left: 30%;
	transform: translate(-50%, -50%);
	color: white;
	text-align: center;
}
h1 {
	margin: 0;
	white-space: nowrap;
	text-align: center;
	font: bold 1.5em/100px "Helvetica Neue", Arial, Helvetica, Geneva, sans-serif;
}
.loginTable {
	margin: 0;
	font-size: 1.5rem;
	color: white;
	text-align: center;
}
.button {
	display: block;
	height: 40px;
	width: 255px;
	background: #FFFFFF;
	border: 2px solid rgba(33, 68, 72, 0.59);
	color: rgba(0, 0, 0, 0.55);
	text-align: center;
	font: bold 1em "Helvetica Neue", Arial, Helvetica, Geneva, sans-serif;
}
a.button {
	text-decoration: none;
}
#para1 {
	text-align: left;
	color: white;
	font:  1.5em "Helvetica Neue", Arial, Helvetica, Geneva, sans-serif;	
}
form table tr td {
	color:white; 
	font: bold 1em "Helvetica Neue", Arial, Helvetica, Geneva, sans-serif;
}
h1 {
	color:white; 
	font: bold 1em "Helvetica Neue", Arial, Helvetica, Geneva, sans-serif;
	font-size: 250%;
}
input[type="text"] {
	display: block;
	margin: 0;
	width: 100%;
	font: bold 1em "Helvetica Neue", Arial, Helvetica, Geneva, sans-serif;
	font-size: 100%;
	appearance: none;
	box-shadow: none;
	border-radius: none;
	padding: 10px;
	border: solid 5px #c9c9c9;
	box-shadow: inset 0 0 0 1px #707070;
	transition: box-shadow 0.3s, border 0.3s;
}
input[type="text"]:focus {
	border: solid 5px #FFB347;
}
input[type="password"] {
	display: block;
	margin: 0;
	width: 100%;
	font: bold 1em "Helvetica Neue", Arial, Helvetica, Geneva, sans-serif;
	font-size: 100%;
	appearance: none;
	box-shadow: none;
	border-radius: none;
	padding: 10px;
	border: solid 5px #c9c9c9;
	box-shadow: inset 0 0 0 1px #707070;
	transition: box-shadow 0.3s, border 0.3s;
}
input[type="password"]:focus {
	border: solid 5px #FFB347;
}
#lists * {
	border-radius:15px;
	background-color:#c9c9c9;
	font: bold 1em "Helvetica Neue", Arial, Helvetica, Geneva, sans-serif;
}
</style>
<body>
	<header>
		<h1>It's time to take care of yourself!</h1><br>
		<form name="test" action="ProcessFormData">
			<table style="width:470px; color:white">
				<tr>
					<td  colspan="2" align="center">
						<% //= ((""+request.getParameter("MessageType")).equals("ERROR"))?"<span style=\"color: red\">":"<span style=\"color: blue\">" %>
						<br/><%=request.getParameter("Message")%><br/><br/>
						<% //= "</span>" %>
						<input Type="button" class="button" value="Back to Registration" onClick="history.go(-1);return true;">
					</td>
				</tr>
			</table>
		</form>
	</header>
</body>
</html>
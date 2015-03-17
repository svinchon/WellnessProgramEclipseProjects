<title>Wellness Program</title>
<style type="text/css">
body {
  background: 
    linear-gradient(
      rgba(0, 0, 0, .9),
      rgba(0, 0, 0, 0.2)
    ),
    url("http://xcp:8080/WellnessProgramRegistration/running-feet.jpg");
  background-size: cover;
  font-family: 'Source Sans Pro', sans-serif;
}
header {
  position: absolute;
  top: 70%;
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
  font-size: 1.2em;
  color: white;
  text-align: center;
}
.button {
   display: block;
    height: 40px;
    width: 140px;
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
	text-align: center;
    color: white;
	font:  0.9em "Helvetica Neue", Arial, Helvetica, Geneva, sans-serif;
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
  padding: 5px;
  border: solid 5px #c9c9c9;
  box-shadow: inset 0 0 0 1px #707070;
  transition: box-shadow 0.3s, border 0.3s;
}
input[type="text"]:focus {

  border: solid 5px #CC9900;
}
input[type="password"] {
  display: block;
  margin: 0;
  width: 100%;
  font-family: Cooper Black, Arial Black, Sans-Serif;
  font-size: 100%;
  appearance: none;
  box-shadow: none;
  border-radius: none;
  padding: 5px;
  border: solid 5px #c9c9c9;
  box-shadow: inset 0 0 0 1px #707070;
  transition: box-shadow 0.3s, border 0.3s;
}
input[type="password"]:focus {
  border: solid 5px #CC9900;
}

</style>
<header>
  <h1>Wellness Program</h1>
    <body id="signin-page">
        <form name="htmlform" method="post" action="http://xcp:8000/WP02/j_spring_security_check">
			<table width="300px" >
				<tr><td><br></td></tr>
				<tr>
					
					<td valign="top" >
					
							<input  type="text" placeholder="Member Name" class="focus" name="j_username" maxlength="50" size="20">
					
					</td>
				</tr>
				<tr>
						
						<td valign="top" >
							<input  type="password" placeholder="Password" class="focus" name="j_password" maxlength="50" size="20">
						</td>
					</tr>
				
				<tr>
				 
				  <td align="left">
				   <input type="submit" class="button" value="Login"><a id="para1" href="http://nkxcp21:8080/WellnessProgramRegistration/">Reset password</a>
				  </td>
				   
				</tr>
				<tr><td><br></td></tr>
				
				<tr>
				  <td colspan="2">
					<a id="para1" href="http://nkxcp21:8080/WellnessProgramRegistration/">Not in the Program? Sign up now</a>
				  </td>
				 </tr>
				 
				</table>   
</div>				
				
</form>

</body>

</header>	
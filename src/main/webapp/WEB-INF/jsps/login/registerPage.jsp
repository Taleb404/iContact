<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register User</title>
</head>
<body>
<form action="registerUser" method="post">
<pre>
<h2>user Registration:</h2>
First Name:<input type="text" name="firstname" />
Last Name:<input type="text" name="lastname" />
User Name:<input type="text" name="email" />
Password :<input type="password" name="password" />
Confirm Password :<input type="password" name="confirmpassword" />
Type:HR <input type="radio" name="type" value="hr" />
Marketing <input type="radio" name="type" value="marketing">
<input type="submit" value="register" />
</pre>
</form>
</body>
</html>
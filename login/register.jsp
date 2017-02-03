<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Home Page</title>
<link  href="style.css" rel="stylesheet"/>
</head>

<body>

<div class="container">
<jsp:include page="top.jsp"></jsp:include>
<jsp:include page="left.jsp"></jsp:include>
<div class="content">
    <h1>Register Now!</h1>
    <form action="submit_register.jsp" method="post">
    <table>
    <tr>	
    	<td>Username</td>
    	<td><input type="text" name="username"></td>
    </tr>
    <tr>	
    	<td>Password</td>
    	<td><input type="password" name="password"></td>
    </tr>
    <tr>	
    	<td>FN</td>
    	<td><input type="text" name="fn"></td>
    </tr>
    <tr>	
    	<td>LN</td>
    	<td><input type="text" name="ln"></td>
    </tr>
    <tr>	
    	<td>gender</td>
    	<td>
        <input type="radio" name="gender" value="male">Male
        <input type="radio" name="gender" value="female"/>Female
        </td>
    </tr>
    <tr>	
    	<td>Email</td>
    	<td><input type="email" name="email"></td>
    </tr>
    <tr>	
    	<td>SQ</td>
    	<td><input type="text" name="sq"></td>
    </tr>
    <tr>	
    	<td>SA</td>
    	<td><input type="text" name="sa"></td>
    </tr>
    <tr>
    	<td colspan="2" align="center"><button>Register</button></td>
    </tr>
    </table>
    </form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</div>
</body>
</html>










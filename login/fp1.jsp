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
    <h1>Forget Password - Step 1</h1>
	<form action="fp2.jsp" method="post">
    Enter your Username:<input type="text" name="username" />
    <br />
	<button>Submit</button>
    </form>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</div>
</body>
</html>







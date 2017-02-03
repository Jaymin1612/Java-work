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
	<%
	String user = request.getParameter("username");
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtoday","root","");
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery("select * from user where username='"+user+"'");
	if(rs.next())
	{
	String sq = rs.getString("question");
	%>
	
    <h1>Forget Password - step 2</h1>
	<form action="fp3.jsp" method="post">
    Security Question:<%=sq%><br />
	Security Answer:
    <input type="text" name="sa" />
    <input type="hidden" name="username" value="<%=user%>"/>
    <br />
	<button>Submit</button>
    </form>
    <%
	}
	else
	{
		out.print("Username not found. You may register first");	
	}
	%>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</div>
</body>
</html>







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
	if(session.getAttribute("user")==null || !session.getAttribute("user").toString().equals("admin"))
	{
		response.sendRedirect("index.jsp?err=please login as admin");
	}
%>
    <h1>Manage Categories</h1>
	<%
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtoday","root","");
	Statement st = con.createStatement();
	String sql = "select * from category;";
	ResultSet rs = st.executeQuery(sql);
	out.print("<table border='1px' align='center'>");
	while(rs.next())
	{
		out.print("<tr>");
		out.print("<td><input type='checkbox'/></td>");
		out.print("<td>"+rs.getString(1)+"</td>");
		out.print("<td>"+rs.getString(2)+"</td>");
		out.print("<td>"+rs.getString(3)+"</td>");
		out.print("<td><input type='button' value='X'/><input type='button' value='U'/></td>");

		out.print("<tr>");
	}
	out.print("</table>");

	%><br />
<br />
<form action="addCategory.jsp" method="post">
<pre>
Category Name: <input type="text" name="cname"/>
Parent Category: <select name='pcid'>
	<option value='null'>None</option>
	<%
		ResultSet rs2 = st.executeQuery("select * from category;");
		while(rs2.next())
		{
			out.print("<option value='"+rs2.getString(1)+"'>"+rs2.getString(2)+"</option>");	
		}
	%>
    
</select>
Category Desc: <input type="text" name="cdesc"/>
<button>Add Category</button>
</pre>
</form>
	
</div>
<jsp:include page="footer.jsp"></jsp:include>
</div>
</body>
</html>
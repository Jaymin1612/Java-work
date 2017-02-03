<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Home Page</title>
<link  href="style.css" rel="stylesheet"/>
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script>
  $(function() {
    $( "#accordion" ).accordion();
  });
  </script>
</head>

<body>

<div class="container">
<jsp:include page="top.jsp"></jsp:include>
<jsp:include page="left.jsp"></jsp:include>
<div class="content">
<%
	String cid = request.getParameter("cid");
	String sql = "select * from category where cid = "+cid+";";
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtoday","root","");
	Statement st = con.createStatement();
	ResultSet rs = st.executeQuery(sql);
	if(!rs.next())
	{
		response.sendRedirect("index.jsp?err=Wrong Category");	
	}
	else
	{
	

%>
    <h1><%=rs.getString("cname")%></h1>
    
<%  
	String sql2 = "select * from item where category_id = "+cid+";";  
	ResultSet rs2 = st.executeQuery(sql2);
	
	out.print("<table border='1px' align='center' width='100%'>");
		while(rs2.next())
		{
			out.print("<tr>");	
			out.print("<td>"+rs2.getString("item_name")+"</td>");	
			out.print("<td>"+rs2.getString("item_price")+"</td>");	
			out.print("<td><input type='text' /><input type='button' value='+'/><input type='button' value='-'/></td>");	
			out.print("<td>0</td>");	
			
			out.print("</tr>");	
		}
		
		
	out.print("</table>");


    }
%>
</div>
<jsp:include page="footer.jsp"></jsp:include>
</div>
</body>
</html>







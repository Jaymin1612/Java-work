<%@ page import="java.sql.*"%>
<%
	String user = request.getParameter("username");
	String pass = request.getParameter("password");
	
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtoday","root","");
	Statement st = con.createStatement();
	String sql = "select * from user where username='"+user+"' and password='"+pass+"';";
	ResultSet rs = st.executeQuery(sql);
	if(rs.next())
	{
		session.setAttribute("user",user);
		response.sendRedirect("profile.jsp");
	}
	else
	{
		response.sendRedirect("index.jsp?err=Wrong username/password");			
	}
%>










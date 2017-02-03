<%@ page import="java.sql.*"%>
<%
	String user = request.getParameter("username");
	String pass = request.getParameter("password");
	String fn = request.getParameter("fn");
	String ln = request.getParameter("ln");
	String email = request.getParameter("email");
	String gender = request.getParameter("gender");
	String sq = request.getParameter("sq");
	String sa = request.getParameter("sa");
	
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtoday","root","");
	Statement st = con.createStatement();
	String sql = "insert into user values('"+user+"','"+pass+"','"+fn+"','"+ln+"','"+sq+"','"+sa+"','"+gender+"','"+email+"');";
	int ans = st.executeUpdate(sql);
	if(ans==1)
	{
		response.sendRedirect("index.jsp?err=Registration done!");
	}
	else
	{
		response.sendRedirect("index.jsp?err=Registration failed!");
	}
%>

















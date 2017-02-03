<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%
	String user = request.getParameter("username");
	String pass = request.getParameter("password");
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtoday","root","");
	Statement st = con.createStatement();
	String sql = "update user set password='"+pass+"' where username='"+user+"';";
	st.executeUpdate(sql);
	response.sendRedirect("index.jsp?err=password reset successfully");
%>
<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>

<%
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtoday","root","");
	Statement st = con.createStatement();
	st.executeUpdate("insert into category values(null, '"+request.getParameter("cname")+"', "+request.getParameter("pcid")+", '"+request.getParameter("cdesc")+"');");
	response.sendRedirect("manageCategories.jsp");
%>
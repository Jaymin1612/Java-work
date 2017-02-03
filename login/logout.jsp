<%
	session.removeAttribute("user");
	response.sendRedirect("index.jsp?err=Successfully Logged out");
%>
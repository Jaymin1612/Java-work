<%
	String cname = request.getParameter("cname");
	
	Cookie ck = new Cookie(cname, "");
	ck.setMaxAge(0);
	response.addCookie(ck);
	response.sendRedirect("index.jsp");
%>
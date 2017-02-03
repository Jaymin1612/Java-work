<%
	String cname = request.getParameter("cname");
	String cvalue = request.getParameter("cvalue");
	String cexp = request.getParameter("cexp");
	
	Cookie ck = new Cookie(cname, cvalue);
	ck.setMaxAge(Integer.parseInt(cexp));
	response.addCookie(ck);
	response.sendRedirect("index.jsp");
%>
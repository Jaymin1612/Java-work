<script src="http://thecodeplayer.com/uploads/js/prefixfree-1.0.7.js" type="text/javascript" type="text/javascript"></script>

<!-- jQuery -->
<script src="http://thecodeplayer.com/uploads/js/jquery-1.7.1.min.js" type="text/javascript"></script>



<%@ page import="java.sql.*"%>
  <div class="sidebar1">
  <div id="accordian">
    <ul class="nav">
      <%
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtoday","root","");
		Statement st = con.createStatement();
		Statement st2 = con.createStatement();
		ResultSet rs = st.executeQuery("select * from category where pcid is null ;");
		while(rs.next())
		{
			out.print("<li>");
			out.print("<h3>"+rs.getString("cname")+"</h3>");
			String sql4 = "select * from category where pcid = "+rs.getString("cid")+";";
			ResultSet rs4 = st2.executeQuery(sql4);
			out.print("<ul>");
			while(rs4.next())
			{
				out.print("<li><a href='category.jsp?cid="+rs4.getString("cid")+"'>"+rs4.getString("cname")+"</a></li>");			
			}
			out.print("</ul>");
			
			out.print("</li>");
		}
		
	  %>
      
    </ul>
    </div>
    <script>
/*jQuery time*/
$(document).ready(function(){
	$("#accordian h3").click(function(){
		//slide up all the link lists
		$("#accordian ul ul").slideUp();
		//slide down the link list below the h3 clicked - only if its closed
		if(!$(this).next().is(":visible"))
		{
			$(this).next().slideDown();
		}
	});
	
});
</script>
    <em></em>
    <h3>UserLogin</h3>
 
 <%
 	if(session.getAttribute("user")==null)
	{
 %>   
    <%
		if(request.getParameter("err")!=null)
		{
			out.print("<span style='color:red;'>"+request.getParameter("err")+"</span>");	
		}
	%>
  	<form action="login.jsp" method="post">
    <table>
    <tr>
    	<td>Username</td>
    </tr>
    <tr>
    	<td><input type="text" name="username" /></td>
    </tr>
    <tr>
    	<td>Password</td>
    </tr>
    <tr>
    	<td><input type="password" name="password" /></td>
    </tr>
    <tr>
    	<td><button>Login</button></td>
    </tr>
    <tr>
    	<td><a href='register.jsp'>SignUp</a>/<a href='fp1.jsp'>Forget Password</a></td>
    </tr>
    </table> 
    </form> 
 <%
	}
	else
	{
 		out.print("Hi,"+session.getAttribute("user")+"<br/><br/>");
		if(session.getAttribute("user").toString().equals("admin"))
		{
		out.print("<a href='manageCategories.jsp'>Manage Categories</a><br/>");
		out.print("<a href='manageItems.jsp'>Manage Items</a><br/>");
		}
		out.print("<a href='changePassword.jsp'>Change Password</a>");
		
		
		out.print("<a href='logout.jsp'><input type='button' value='Logout'/></a>");
				
	}
 %>
  </div>






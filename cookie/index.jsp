<html>
<head>
	<script>
		function upd(cname)
		{
			if(document.getElementById("upd_"+cname).value=="U")
			{
			document.getElementById("upd_"+cname).value="Save";
			var cvalue = document.getElementById("cv_"+cname).innerHTML;
			var txt = "<input id='fv_"+cname+"' type='text' value='"+cvalue+"'>";
			document.getElementById("cv_"+cname).innerHTML = txt;
			}
			else
			{
				var fcvalue = document.getElementById("fv_"+cname).value;
				var cexp = prompt("Enter new Exp time:","3600");
				var url = "addCk.jsp?cname="+cname+"&cvalue="+fcvalue+"&cexp="+cexp;
				window.location=url;
				//alert(fcvalue);	
			}
		}
	</script>
</head>
<body>
	<%
		Cookie[] ck = request.getCookies();
		out.print("<table border='1px' align='center'>");
			out.print("<tr>");
			out.print("<td></td>");
			out.print("<td>Cookie Name</td>");
			out.print("<td>Cookie Value</td>");
			out.print("<td>Operations</td>");
			out.print("</tr>");
		if(ck!=null)
		for(int i=0;i<ck.length;i++)
		{
			out.print("<tr>");
			out.print("<td><input type='checkbox'/></td>");
			out.print("<td>"+ck[i].getName()+"</td>");
			out.print("<td id='cv_"+ck[i].getName()+"'>"+ck[i].getValue()+"</td>");
			out.print("<td><a href='delCk.jsp?cname="+ck[i].getName()+"'><input type='button' value='X'/></a>");
out.print("<input id='upd_"+ck[i].getName()+"' onclick=\"upd('"+ck[i].getName()+"');\" type='button' value='U' />");
			out.print("</td>");
			out.print("</tr>");
			
		}
		out.print("</table>");

	%>

<br>
<br>
<br>
<h2>Add Cookie</h2>
<form action="addCk.jsp" method="post">
Cookie Name: <input type="text" name="cname"/><br>
Cookie value: <input type="text" name="cvalue"/><br>
Cookie Exp: <input type="text" name="cexp"/><br>
<button>Submit</button>
</form>
</body>


</html>
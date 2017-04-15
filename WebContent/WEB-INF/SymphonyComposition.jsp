<%@ page import="controllers.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Symphony Composition</title>

<style>
table, td, th {
    border: 1px solid black;
}

table {
    border-collapse: collapse;
    width: 50%;
}

td {
	padding-left: 10px;
    height: 35px;
    vertical-align: bottom;
}
</style>
</head>
<body>
<% Composer composer = (Composer)request.getAttribute("composer"); %>
<% Integer currentIndex = (Integer)request.getAttribute("currentIndex"); %>
<% Integer nextIndex = (Integer)request.getAttribute("nextIndex"); %>
<% Integer nextPage = (Integer)request.getAttribute("nextPage"); %>
<% ArrayList<Object> rows = (ArrayList)request.getAttribute("rows"); %>

<% ListIterator iter = rows.listIterator(); %>

<h2> <%=composer.getName()%></h2>

<% if (nextIndex >= 0) {%>
	<a href="/SymphonyProject/SymphonyComposition?composerIndex=<%=nextIndex%>">Next Composer</a>
<% } %>

<table>
<% while (iter.hasNext()){ 
	Object row = iter.next();
	if (row instanceof Composition){
	%>
		<tr>
			<td><%=((Composition)row).getName()%></td>
			<td></td>
		</tr>
	<%}else{%>	
		<tr>
			<td></td>
			<td><%=((Movement)row).getName()%></td>
		</tr>
	<%} %>
<%}%>
</table>



<% if (nextPage >= 0) {%>
	<a href="/SymphonyProject/SymphonyComposition?composerIndex=<%=currentIndex%>&page=<%=nextPage%>">Next Page</a>
<% } %>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
	Hello BlackJack
	<%
	String msg = (String) request.getAttribute("message");

	%>
	<div class="header-notice">
		<%
			if (msg != null) {
		%>
		<p><%=msg%></p>
		<%
			}
		%>
	</div>
</body>
</html>
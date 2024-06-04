<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hit or stand</title>
<%
	Boolean endFlag = false;
	if ((Boolean) request.getAttribute("endFlag") != null) {
		endFlag = (Boolean) request.getAttribute("endFlag");
	}
	Boolean splittable = (Boolean) request.getAttribute("SPLITTABLE");
	splittable = true;
	//スプリット可能かどうか決めてボタンを追加しボタンを押すといろいろ分離するような仕様にする.
	//js使った方が楽かも
%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/components/blackjack/style.css">
</head>
<body>
	<form action="<%=request.getContextPath()%>/BJServlet" method="get">
		<button type="submit" value="hit" name="opt" <%if (endFlag == true) {%>
			disabled <%}%>>hit</button>
		<button type="submit" value="stand" name="opt" <%if (endFlag == true) {%>
			disabled <%}%>>stand</button>
		<button type="submit" value="split" name="opt" <%if(!splittable){ %> style="display:none" <%} %> >split</button>
	</form>
</body>
</html>
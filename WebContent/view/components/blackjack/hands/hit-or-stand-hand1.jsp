<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/components/blackjack/style.css">
	<%
	Boolean endFlag1 = false;//初期値
	if(session.getAttribute("endFlag1") != null){
	endFlag1 = (Boolean) session.getAttribute("endFlag1");
	}

	%>
</head>
<body>

<form action="<%=request.getContextPath()%>/BJSplitServlet" method="post">
		<button type="submit" value="hit" name="opt1"
			<%if (endFlag1 == true) {%> disabled <%}%>>hit</button>
		<button type="submit" value="stand" name="opt1"
			<%if (endFlag1 == true) {%> disabled <%}%>>stand</button>
</form>


</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.FlagOwner"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/components/blackjack/style.css">
</head>
<body>

	<form action="<%=request.getContextPath()%>/BJSplitServlet"
		method="post">
		<button class="form-button" type="submit" value="hit" name="opt1"
			<%if (FlagOwner.checkPlayer1End()) {%> style="display:none" <%}%>>hit</button>
		<button class="form-button" type="submit" value="stand" name="opt1"
			<%if (FlagOwner.checkPlayer1End()) {%> style="display:none" <%}%>>stand</button>
	</form>


</body>
</html>
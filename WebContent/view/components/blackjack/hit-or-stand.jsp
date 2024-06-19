<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.FlagOwner"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>hit or stand</title>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/components/blackjack/style.css">
</head>
<body>
<%if(!FlagOwner.checkSplitting()){ %>
	<form action="<%=request.getContextPath()%>/BJServlet" method="get">
		<button class="form-button" type="submit" value="hit" name="opt"
			<%if (FlagOwner.checkUsualGameEnd()) {%> style="display:none" <%}%>>hit</button>
		<button class="form-button stand" type="submit" value="stand" name="opt"
			<%if (FlagOwner.checkUsualGameEnd()) {%> style="display:none" <%}%>>stand</button>
		<button class="form-button" type="submit" value="split" name="opt" onClick="splitHand()"
			id="split-button" <%if (!FlagOwner.checkSplittable()) {%> style="display: none" <%}%>>split</button>
	</form>
	<%} %>
</body>
</html>
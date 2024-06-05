<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="blackjack.Deck"%>
<%@ page import="model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/components/blackjack/style.css">
<%
	Deck deck = (Deck) session.getAttribute("DECK");
	User user = (User) session.getAttribute("USER");
	String msg = (String) request.getAttribute("msg");
	String msg1 = (String) request.getAttribute("msg1");
	String msg2 = (String) request.getAttribute("msg2");
%>
</head>
<body>
	<div class="message">
		<!--  <div class="rem">
		<h2>残deck.size()e() %>枚</h2>
	</div>
	-->
		<div class="numOfTips">
			現在のチップ枚数:
			<%=user.getNumberOfTips()%>
		</div>
		<%
			if (msg != null) {
		%>
		<h3><%=msg%></h3>
		<%
			}
		%>
				<%
			if (msg1 != null) {
		%>
		<h3>手札1は<%=msg1%></h3>
		<%
			}
		%>
				<%
			if (msg2 != null) {
		%>
		<h3>手札2は<%=msg2%></h3>
		<%
			}
		%>
	</div>

</body>
</html>
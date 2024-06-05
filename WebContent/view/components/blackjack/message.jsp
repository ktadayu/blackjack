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
%>
</head>
<body>
	<div class="message-container">
		<!--  <div class="rem">
		<h2>残deck.size()e() %>枚</h2>
	</div>
	-->
		<div class="numOfTips">
			現在のチップ枚数:
			<%=user.getNumberOfTips()%>
		</div>
	</div>

</body>
</html>
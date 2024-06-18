<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="blackjack.Deck"%>
<%@ page import="model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ゲームプレイ中のnav</title>
</head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/components/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@100..900&display=swap"
	rel="stylesheet">
<%
	Deck deck = (Deck) session.getAttribute("DECK");
	User user = (User) session.getAttribute("USER");
	String msg = (String) request.getAttribute("msg");
	String msg1 = (String) request.getAttribute("msg1");
	String msg2 = (String) request.getAttribute("msg2");
%>
<body>
	<div class="header-container">
	<nav class="header">
	<div class="numOfTips">現在のチップ枚数: <%=user.getNumberOfTips()%></div>
	<div>
	<% //勝敗の表示  %>
			<%
			if (msg != null) {
			%>
		<span class="grid1"><%=msg %></span>
			<%
			}
			%>
			<%
				if (msg1 != null) {
			%>
		<span class="grid1"><%=msg1 %></span>
			<%
			}
			%>
			<%
				if (msg2 != null) {
			%>
		<span class="grid1"><%=msg2 %></span>
			<%
			}
			%>
	</div>
	<div><a href="<%=request.getContextPath()%>/ToGameTopServlet">ゲームトップへ戻る</a></div>
	</nav>
	</div>

</body>
</html>
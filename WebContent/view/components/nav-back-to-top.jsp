<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="blackjack.Deck"%>
<%@ page import="model.User"%>

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/components/style.css">

<%
	Deck deck = (Deck) session.getAttribute("DECK");
	User user = (User) session.getAttribute("USER");
	String message = (String) request.getAttribute("message"); //エラー文
	//勝敗に関するメッセージ
	String msg = (String) request.getAttribute("msg");
	String msg1 = (String) request.getAttribute("msg1");
	String msg2 = (String) request.getAttribute("msg2");
%>
<body>
	<div class="header-container">
	<nav class="header">
	<div class="numOfTips">現在のチップ枚数: <%=user.getNumberOfTips()%></div>
	<div class="messages">
	<% //勝敗の表示  %>
				<%
			if (message != null) {
			%>
		<h4> <%=message %>	</h4>
			<%
			}
			%>
			<%
			if (msg != null) {
			%>
		<span ><%=msg %></span>
			<%
			}
			%>
			<%
				if (msg1 != null) {
			%>
		<h4>手札1は<span ><%=msg1 %></span></h4>
			<%
			}
			%>
			<%
				if (msg2 != null) {
			%>
		<h4>手札2は<span ><%=msg2 %></span></h4>
			<%
			}
			%>
	</div>
	<div><a href="<%=request.getContextPath()%>/ToGameTopServlet">ゲームトップへ戻る</a></div>
	</nav>
	</div>

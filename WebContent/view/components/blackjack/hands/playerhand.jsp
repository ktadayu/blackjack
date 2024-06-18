<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="blackjack.players.*"%>
<%@ page import="blackjack.Card"%>
<%@ page import="blackjack.Hand"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/components/blackjack/style.css">
</head>
<%
	Player player = (Player) session.getAttribute("PLAYER");
	Player player1 = (Player) session.getAttribute("plyr1");
	Player player2 = (Player) session.getAttribute("plyr2");

	String imgFileRootName = request.getContextPath() + "/img/trump/card_";
%>
<body>

	<div class="player" id="playerhand1"
		<% if (player1 != null && player1.getHand() != null) {%>
		style="display: none" <%}%> >
		<div class="playerhand1">
			<h3>プレイヤー手札:</h3>
			<%
				for (Card card : player.getHand().getCards()) {
			%>
			<img src="<%=imgFileRootName + card%>.png" alt="<%=card%>">
			<%
				}
			%>
		</div>
		<div class="player1-sum">
		<h4>
			合計
			<%=player.getHand().totalValue()%>
		</h4>
		</div>

	</div>

	<!-- スプリット時 -->
	<div class="players">
	<%
		if (player1 != null && player1.getHand() != null && player2.getHand() != null) {
	%>
	<div class="playerhands-2">
		<div class="hand1">
			<h3>プレイヤー手札1:</h3>
			<%
				for (Card card : player1.getHand().getCards()) {
			%>
			<img src="<%=imgFileRootName + card%>.png" alt="<%=card%>">
			<%
				}
			%>
			<h4>
				合計
				<%=player1.getHand().totalValue()%>
			</h4>

			<jsp:include page="./hit-or-stand-hand1.jsp" />
		</div>
		<div class="hand2">
			<h3>プレイヤー手札2:</h3>
			<%
				for (Card card : player2.getHand().getCards()) {
			%>
			<img src="<%=imgFileRootName + card%>.png" alt="<%=card%>">
			<%
				}
			%>
			<h4>
				合計
				<%=player2.getHand().totalValue()%>
			</h4>

			<jsp:include page="./hit-or-stand-hand2.jsp" />
		</div>
	</div>
	<%
		}
	%>
	</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="blackjack.Deck"%>
<%@ page import="blackjack.Hand"%>
<%@ page import="blackjack.Card"%>
<%@ page import="blackjack.players.*"%>
<%@ page import="model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
img {
	width: 80px;
	height: 120px
}
</style>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/components/blackjack/style.css">
<%
	Deck deck = (Deck) session.getAttribute("DECK");
	Player player = (Player) session.getAttribute("PLAYER");
	Dealer dealer = (Dealer) session.getAttribute("DEALER");
	Hand playerHand = player.getHand();
	Hand dealerHand = dealer.getHand();
	Boolean flag = (Boolean) request.getAttribute("FLAG");
	Boolean SPLITTABLE = (Boolean) request.getAttribute("SPLITTABLE");
%>
</head>
<body>

	<%
		String imgFileRootName = request.getContextPath() + "/img/trump/card_";
	%>

	<section class="section-gamefield">
		<div class="message">
			<jsp:include page="./message.jsp" />
		</div>
		<div>
			<div class="dealerhand">
				<h3>ディーラー手札:</h3>
				<%
					//ディーラー2枚目のカードを裏向きに表示させておくための場合分け
					for (int i = 0; i < dealerHand.size(); i++) {
						if (i == 1 && flag != null) {
				%>
				<img src="<%=request.getContextPath() %>/img/card_back.png"
					alt="back of Card">
				<%
					} else {
				%>
				<img src="<%=imgFileRootName + dealerHand.get(i)%>.png"
					alt="<%=dealerHand.get(i)%>">
				<%
					}
					}
				%>
				<h4>
					合計
					<%=dealerHand.totalValue()%>
				</h4>
			</div>
			<div class="playerhands">
				<div class="playerhand-1">
					<h3>プレイヤー手札:</h3>
					<%
						for (Card card : playerHand.getCards()) {
					%>
					<img src="<%=imgFileRootName + card%>.png" alt="<%=card%>">
					<%
						}
					%>
					<h4>
						合計
						<%=playerHand.totalValue()%>
					</h4>
				</div>
				<!-- 明日修正 -->
				<div class="playerhand-2" <% if(SPLITTABLE == null){ %> style="display: none"  <% }%> >
					<h3>プレイヤー手札:</h3>
					<%
						for (Card card : playerHand.getCards()) {
					%>
					<img src="<%=imgFileRootName + card%>.png" alt="<%=card%>">
					<%
						}
					%>
					<h4>
						合計
						<%=playerHand.totalValue()%>
					</h4>
				</div>
			</div>
		</div>
	</section>

</body>
</html>
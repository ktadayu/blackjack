<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="blackjack.Hand"%>
<%@ page import="blackjack.players.*"%>
<%@ page import="model.FlagOwner"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/components/blackjack/hands/style.css" />
</head>
<%
	Dealer dealer = (Dealer) session.getAttribute("DEALER");
	Hand dealerHand = dealer.getHand();
	String imgFileRootName = request.getContextPath() + "/img/trump/card_";
%>
<body>
	<div class="dealer">

		<div class="dealerhand">
			<h3>ディーラー手札:</h3>
			<div class="card-container">

				<%
					//&& !FlagOwner.checkEndGame()
					//ディーラー2枚目のカードを裏向きに表示させておくための場合分け
					for (int i = 0; i < dealerHand.size(); i++) {
				if(i == 0){
				%>
				<div class="first-dealer-card">
					<img src="<%=imgFileRootName + dealerHand.get(i)%>.png"
						alt="<%=dealerHand.get(i)%>" class="dealer-card-img-<%=i%>">
				</div>
				<%
				}
				if (i == 1) {
				%>
				<div class="front-back-container inline-block <%if(FlagOwner.checkEndGame()){ %> flip <% } %>">
					<span class="front-card">
					<img src="<%=imgFileRootName + dealerHand.get(i)%>.png"
						alt="front of Card" class="card">
					</span>
					<span class="back-card"> <img
						src="<%=request.getContextPath()%>/img/card_back.png"
						alt="back of Card" class="card">
					</span>
				</div>
				<img style="opacity: 0" />
				<%
					} else if (i > 1) {
				%>
				<div class="inline-block">
					<img src="<%=imgFileRootName + dealerHand.get(i)%>.png"
						alt="<%=dealerHand.get(i)%>" class="dealer-card-img-<%=i%>">
				</div>
			<%
				}
				}
			%>

			</div>
			<div class="dealer-sum">
				<%
					if (FlagOwner.checkEndGame()) {
				%>
				<h4>
					合計
					<%=dealerHand.totalValue()%>
				</h4>
				<%
					}
				%>
			</div>
		</div>
	</div>
</body>
</html>
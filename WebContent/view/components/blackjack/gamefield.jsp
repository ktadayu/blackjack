<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="blackjack.Deck" %>
<%@ page import="blackjack.Hand" %>
<%@ page import="blackjack.Card" %>
<%@ page import="blackjack.players.*" %>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
img {
width:120px;
height:170px
}
</style>
<link rel="stylesheet" href="<%= request.getContextPath() %>/view/components/blackjack/style.css">
<%
Deck deck = (Deck) session.getAttribute("DECK");
Player player =(Player) session.getAttribute("PLAYER");
Dealer dealer = (Dealer) session.getAttribute("DEALER");
Hand playerHand = player.getHand();
Hand dealerHand = dealer.getHand();
%>
</head>
<body>

<%
String imgFileRootName = request.getContextPath() + "/img/trump/card_";
%>

<div>
	<div class="dealerhand">
		<h3>ディーラー手札:</h3>
		 <%for(Card card : dealerHand.getCards() ){%>
			<img src="<%=imgFileRootName + card%>.png"  alt="<%=card %>">
		 <% }%>
	</div>
	<div class="playerhand">
		<h3>プレイヤー手札: </h3>
		 <%for(Card card : playerHand.getCards() ){%>
			<img src="<%=imgFileRootName + card%>.png"  alt="<%=card %>">
		 <% }%>
	</div>
	<h3>合計 <%=playerHand.totalValue() %> </h3>
</div>

</body>
</html>
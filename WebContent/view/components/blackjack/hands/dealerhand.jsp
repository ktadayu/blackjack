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
</head>
<%
	Dealer dealer = (Dealer) session.getAttribute("DEALER");
	Hand dealerHand = dealer.getHand();
	String imgFileRootName = request.getContextPath() + "/img/trump/card_";
%>
<body>


	<h3>ディーラー手札:</h3>
	<%
		//ディーラー2枚目のカードを裏向きに表示させておくための場合分け
		for (int i = 0; i < dealerHand.size(); i++) {
			if (i == 1 && !FlagOwner.checkEnd()) {
	%>
	<img src="<%=request.getContextPath()%>/img/card_back.png"
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
	<%if(FlagOwner.checkEnd()){ %>
	<h4>
		合計
		<%=dealerHand.totalValue()%>
	</h4>
	<% } %>

</body>
</html>
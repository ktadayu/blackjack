<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="blackjack.Deck" %>
<%@ page import="blackjack.Hand" %>
<%@ page import="blackjack.players.*" %>
<%@ page import="model.User" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BlackJack</title>
<%
Deck deck = (Deck) session.getAttribute("DECK");
Player player =(Player) session.getAttribute("PLAYER");
Dealer dealer = (Dealer) session.getAttribute("DEALER");
User user = (User) session.getAttribute("USER");
int betPoint = (int) session.getAttribute("BETPOINT");

Hand playerHand = player.getHand();
Hand dealerHand = dealer.getHand();

Boolean dic = true ;
if ((Boolean) request.getAttribute("dic") != null ) {dic = (Boolean) request.getAttribute("dic") ; }

String msg;
if ((String) request.getAttribute("msg") != null ){
 msg = (String) request.getAttribute("msg");
}else{msg = "" ;}
%>
</head>
<body>

<nav class="nav-header">
    <div class="header">
        <a href="BlackJackServlet">ゲームトップへ戻る</a>
    </div>
</nav>

<%String message = (String) request.getAttribute("message"); %>
<%if(message != null){ %>
<div>
<%=message %>
</div>
<%} %>

<div>
	<div>
		<h2>残り<%=deck.size() %>枚</h2>
	</div>
	<div>
		現在のチップ枚数: <%=user.getNumberOfTips() %>
	</div>
</div>

<div>
	<div>
		<h3>ディーラー手札:  <%= dealerHand.getCards() %></h3>
		<h3>合計: <%=dealerHand.totalValue() %></h3>
	</div>
	<div>
		<h3>プレイヤー手札: <%= playerHand.getCards() %></h3>
		<h3>合計 <%=playerHand.totalValue() %> </h3>
	</div>
</div>


<form action="<%=request.getContextPath() %>/BJServlet" method="get" >
<button type="submit" value="hit" name="opt" <%if(dic == false){ %> disabled <%}%>>hit</button>
<button type="submit" value="stand" name="opt"<%if(dic == false){ %> disabled <%}%>>stand</button>
</form>


<%if(msg !=null){  %>
<h3><%=msg %></h3>
<% } %>

<%if(dic == false) {%>
<div class=div-opt-game>
<form class="game_start_form" action="<%= request.getContextPath() %>/view/game/game_betting.jsp"> <!-- formかaにするかで迷う -->
	<div class="div_game_start_button" >
		<button class="game_start_button" type="submit" > ベット額を変えて挑戦</button>
	</div>
</form>
<form class="game_start_form" action="<%=request.getContextPath() %>/BJServlet" method="post"> <!-- formかaにするかで迷う -->
	<div class="div_game_start_button" >
		<button class="game_start_button" type="submit" > 同じベット額で挑戦</button>
	</div>
</form>
</div>
<% } %>


</body>

</html>
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
User user = (User) session.getAttribute("USER");

String msg = (String) request.getAttribute("msg");

%>
</head>
<jsp:include page="../components/nav-game.jsp"/>
<body>

<div>
	<div>
		<h2>残り<%=deck.size() %>枚</h2>
	</div>
	<div>
		現在のチップ枚数: <%=user.getNumberOfTips() %>
	</div>
</div>

<!--  カードを展開する -->
<jsp:include page="../components/blackjack/gamefield.jsp"/>

<!--  hitかstandの選択 -->
<jsp:include page="../components/blackjack/hit-or-stand.jsp"/>

<%if(msg !=null){  %>
<h3><%=msg %></h3>
<% } %>

<!--  ベット額を変えるか、同じベット額で再プレイ -->
<jsp:include page="../components/blackjack/replay.jsp"/>

</body>
</html>
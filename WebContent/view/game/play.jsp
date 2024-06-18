<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="blackjack.Deck"%>
<%@ page import="blackjack.Hand"%>
<%@ page import="blackjack.players.*"%>
<%@ page import="model.User"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>BlackJack</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/game/style/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@100..900&display=swap"
	rel="stylesheet">
<%
	User user = (User) session.getAttribute("USER");
%>
</head>
<body>
	<!-- ゲームナビゲーションバー -->
	<jsp:include page="../components/nav-back-to-top.jsp" />

	<section class="game-board">
	<!--  カードを展開する -->
	<div class="gamefield">
	<jsp:include page="../components/blackjack/gamefield.jsp" />
	</div>

	<div class="hit-or-stand" >
	<!-- hitr or stand, splitの選択-->
	<jsp:include page="../components/blackjack/hit-or-stand.jsp" />
	</div>

	<div  class="replay">
	<!--  ベット額を変えるか、同じベット額で再プレイ -->
	<jsp:include page="../components/blackjack/replay.jsp" />
	</div>
	</section>
</body>
</html>
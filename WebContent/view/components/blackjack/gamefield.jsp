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
</head>
<body>

	<section class="section-gamefield">

		<div>
			<!--  ディーラーの手札 -->
				<jsp:include page="./hands/dealerhand.jsp" />

			<!-- プレイヤーの手札 -->
			<div class="playerhands">
				<jsp:include page="./hands/playerhand.jsp" />
			</div>
		</div>
	</section>

</body>
</html>
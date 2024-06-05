<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.FlagOwner"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/components/blackjack/style.css">
</head>
<body>

	<%
		if ((FlagOwner.checkUsualGameEnd() && !FlagOwner.checkSplitting())|| FlagOwner.checkEndGame()) {
	%>
	<div class=div-opt-game>
		<form class="game_start_form"
			action="<%=request.getContextPath()%>/view/game/game_betting.jsp">
			<div class="div_game_start_button">
				<button class="game_start_button" type="submit">
					ベット額を変えて挑戦</button>
			</div>
		</form>
		<form class="game_start_form"
			action="<%=request.getContextPath()%>/BJServlet" method="post">
			<div class="div_game_start_button">
				<button class="game_start_button" type="submit">同じベット額で挑戦</button>
			</div>
		</form>
	</div>
	<%
		}
	%>


</body>
</html>
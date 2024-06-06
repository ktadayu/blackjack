<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.User"%>
<%@ page import="model.History"%>
<%@ page import="java.util.List"%>
<%@ page import="dao.UserDao"%>
<%@ page import="dao.HistoryDao"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BlackJack</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/game/style/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@100..900&display=swap"
	rel="stylesheet">
<%
	User USER = (User) session.getAttribute("USER");
	List<User> ranking = (List<User>) request.getAttribute("ranking");
	String msg = (String) request.getAttribute("message");
%>
</head>
<jsp:include page="../components/nav-top.jsp" />
<body>

	<section>
		<div class="section-greeting">
			<%
				if (msg != null) {
			%>
			<h3 class="alert"><%=msg%></h3>
			<%
				}
			%>
			<h2>
				ようこそ
				<%=USER.getUserNickname()%>さん
			</h2>
			<form class="game_start_form"
				action="<%=request.getContextPath()%>/view/game/game_betting.jsp">
				<!-- formかaにするかで迷う -->
				<div class="div_game_start_button">
					<button class="game_start_button" type="submit">
						ゲームをはじめる(ディール？)</button>
				</div>
			</form>
		</div>
		<div class="ranking">
			<div class="ranking-container">
				<h1 class="ranking-header">ランクTOP5</h1>
				<table>
					<tr>
						<th class="table-header">順位</th>
						<th class="table-header">ニックネーム</th>
						<th class="table-header">チップ枚数</th>
					<!-- <th class="table-header">勝率</th> -->
					</tr>
					<%
						int i = 1;
					%>
					<%
						for (User user : ranking) {
					%>
					<tr>
						<th><%=i%></th>
						<th><%=user.getUserNickname()%></th>
						<th><%=user.getNumberOfTips()%></th>
					<!-- <th><%=user.getRate()%></th>-->
					</tr>
					<%
						i++;
					%>
					<%
						}
					%>
				</table>
			</div>
			<!-- jsp記述 -->
		</div>
	</section>
	<footer class="footer">
		<a href="<%=request.getContextPath()%>/UserServlet">管理者ページ</a> <a
			href="<%=request.getContextPath()%>/view/users/leave.jsp">ユーザー消去</a>
	</footer>
</body>
</html>
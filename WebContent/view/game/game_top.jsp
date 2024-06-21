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
	User USER = (User) session.getAttribute("USER");
	List<User> ranking = (List<User>) request.getAttribute("ranking");
	String msg = (String) request.getAttribute("message");
%>
</head>
<body>

<jsp:include page="../components/nav-top.jsp" />

<%
	if (msg == null) {
%>
  <div class="animation-container">
      <div class="animation-characters">
        <span class="character">B</span>
        <span class="character">L</span>
        <span class="character">A</span>
        <span class="character">C</span>
        <span class="character">K</span>
        <span class="character">J</span>
        <span class="character">A</span>
        <span class="character">C</span>
        <span class="character">K</span>
      </div>
    </div>
 <% } %>

<section class="global-section">
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
			<form class=""
				action="<%=request.getContextPath()%>/view/game/game_betting.jsp">
				<!-- formかaにするかで迷う -->
				<div class="div_game_start_button">
					<button class="form-button" type="submit">
						ゲームをはじめる</button>
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
	</section>
	<footer class="footer">
		<a href="<%=request.getContextPath()%>/UserServlet">管理者ページ</a>
	</footer>
<script src="<%=request.getContextPath()%>/view/game/app/animation.js"></script>
</body>
</html>
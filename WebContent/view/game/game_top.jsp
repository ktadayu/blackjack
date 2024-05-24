<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BlackJack</title>
</head>
<body>

<section class="header-section">
<div class="header">
<a href="<%=request.getContextPath() %>/LoginServlet">ログアウト</a>
</div>
</section>

<section>
<% User USER = (User) session.getAttribute("USER"); %>
<% List<User> users = (List<User>) session.getAttribute("TOPUSERLIST"); %>
<div class="section-greeting">
<% String msg = (String) request.getAttribute("message");%>
<% if(msg != null){%>
<h3 class="alert"><%=msg %></h3>
<%} %>
<h2>ようこそ <%= USER.getUserNickname() %>さん</h2>
<form class="game_start_form" action="<%= request.getContextPath() %>/view/game/game_betting.jsp"> <!-- formかaにするかで迷う -->
	<div class="div_game_start_button" >
		<button class="game_start_button" type="submit" > ゲームをはじめる(ディール？)</button>
	</div>
</form>
</div>
<div class="ranking">


 <div class="ranking-container">
 	<h1 class="ranking-header">ランクTOP5</h1>
        <table>
            <tr>
            	<th class="table-rank">順位</th>
                <th class="table-header">ニックネーム</th>
                <th class="table-header">チップ枚数</th>
                <th class="table-header">勝率</th>
            </tr>
            <% int i=1; %>
            <% for(User user: users){ %>
            <tr>
            	<th><%= i %></th>
                <th><%=user.getUserNickname() %></th>
                <th><%=user.getNumberOfTips()%></th>
                <th>勝率</th>
            </tr>
            <% i++; %>
            <% } %>
        </table>
    </div>
<!-- jsp記述 -->
</div>
</section>

  <footer class="footer">

  	<a href="<%= request.getContextPath()%>/UserServlet">管理者ページ</a>
  	<a href="<%= request.getContextPath()%>/view/users/leave.jsp">ユーザー消去</a>

  </footer>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ page import="model.History" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.UserDao" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BlackJack</title>
<%
User USER = (User) session.getAttribute("USER");
UserDao newUserDao = new UserDao();
List<User> users = newUserDao.selectTopRateUsers();
String msg = (String) request.getAttribute("message");
%>
</head>
<body>

<section class="header-section">
<div class="header">
<a href="<%=request.getContextPath() %>/LoginServlet">ログアウト</a>
<a href="<%=request.getContextPath() %>/view/users/my_history.jsp">戦績確認</a>
</div>
</section>

<section>
<div class="section-greeting">
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
                <th><%=user.getNumberOfTips() %></th>
                <th><%=user.getRate()%></th>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%@ page import="model.History" %>
<%@ page import="dao.UserDao" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>戦績</title>
</head>
<body>
<%  %>

<nav class="nav-header">
    <div class="header">
        <a href="<%=request.getContextPath() %>/BlackJackServlet">ゲームトップへ戻る</a>
    </div>
</nav>

<%
User USER = (User) session.getAttribute("USER");
UserDao newUserDao = new UserDao();
List<History> historyList = newUserDao.selectAllHistory(USER);
%>

<div>
<table>
	<tr>
		<th class="table-header">No</th>
		<th class="table-header">チップ増減</th>
		<th class="table-header">日時</th>
	</tr>
	<% int i= 1; %>
<% for(History history: historyList ){ %>
	<tr>
		<th><%=i %></th>
		<th><%=history.getAmountOfChanges() %></th>
		<th><%=history.getTimeStamp()%></th>
	</tr>
<% i++ ;%>
<% } %>
</table>
</div>

</body>
</html>
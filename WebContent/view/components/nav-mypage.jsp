<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.User"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/components/style.css">
<% //マイページ用ヘッダー %>

<% User USER = (User) session.getAttribute("USER"); %>
<div class="header-container">
	<div class="header mypage">
		<a href="<%=request.getContextPath()%>/view/users/my_history.jsp">戦績</a>
		<a href="<%=request.getContextPath()%>/view/users/leave.jsp">このユーザーを削除</a>
		<a href="<%=request.getContextPath()%>/ToGameTopServlet">ゲームトップへ戻る</a>
	</div>
</div>

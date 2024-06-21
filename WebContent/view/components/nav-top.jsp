<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.User"%>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/components/style.css">

<%
User USER = (User) session.getAttribute("USER");
%>
<div class="header-container">
	<% if(USER != null){ %>
	<div class="header toppage">
		<a href="<%=request.getContextPath()%>/view/users/my_history.jsp">マイページ</a>
		<a href="<%=request.getContextPath()%>/LoginServlet">ログアウト</a>
	</div>
	<% }else{ %>
	<div class="header loginpage">
		<a href="#">このサイトについて</a>
		<a href="#">ブラックジャックのルール</a>
		<%if(request.getParameter("signUp") != null){%>
		<a href="<%=request.getContextPath()%>/ToGameTopServlet">ゲームトップへ戻る</a>
		<%}%>
	</div>
	<% } %>
	<button class="btn-for-mobile">
		<span></span>
		<span></span>
		<span></span>
	</button>
</div>

<script src="<%=request.getContextPath()%>/view/components/mobile-btn.js"></script>

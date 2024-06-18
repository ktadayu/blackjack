<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.User"%>
<%
	User USER = (User) session.getAttribute("USER");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>ユーザーの削除</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/users/style/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@100..900&display=swap" rel="stylesheet">
</head>
<body>
<jsp:include page="../components/nav-top.jsp" />
	<section class="section">

		<h1 class="section-headline">ユーザーの削除</h1>
		<div class="header-notice">
			<%
				String msg = (String) request.getAttribute("message");
			%>
			<%
				if (msg != null) {
			%>
			<p><%=msg%></p>
			<%
				} else {
				}
			%>
		</div>

		<form action="<%=request.getContextPath()%>/LeaveServlet"
			method="post" class="form">

			<table class="form-table">
				<tr>
					<th class="form-table-headline"><label for="id">ニックネーム（自動入力）</label></th>
					<td class="form-table-data">
					<span class="form-table-user-nickname"><%=USER.getUserNickname()%></span>
					</td>
				</tr>
				<tr>
					<th class="form-table-headline"><label for="pass">パスワード </label></th>
					<td class="form-table-data">
					<input type="text" class="input" name="user_password" id="pass" />
					</td>
				</tr>
			</table>
			<div >
				<button class="form-button" type="submit">このユーザーを削除する</button>
			</div>
			     
		</form>
	</section>
</body>
</html>
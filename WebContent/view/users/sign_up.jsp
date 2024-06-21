<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>登録画面</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/users/style/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@100..900&display=swap"
	rel="stylesheet">
<%
	String msg = (String) request.getAttribute("message");
%>
</head>
<body>
<jsp:include page="../components/nav-top.jsp" />

	<section class="section">
		<h1 class="section-headline">登録</h1>

		<div class="header-notice">
			<%
				if (msg != null) {
			%>
			<p><%=msg%></p>
			<%
				} else {
				}
			%>
		</div>
		<form action="<%=request.getContextPath()%>/SignupServlet"
			method="post" class="form">
			<table class="form-table">
				<tr>
					<th class="form-table-headline"><label for="id">ユーザー名</label></th>
					<td class="form-table-data">
					<input type="text" class="input" name="user_name" id="id" required />
					</td>
				</tr>
				<tr>
					<th class="form-table-headline"><label for="pass1">
							パスワード </label></th>
					<td class="form-table-data">
					<input type="password" class="input" name="user_password1" id="pass1" required />
					</td>
				</tr>
				<tr>
					<th class="form-table-headline"><label for="pass2">
							パスワード(確認用) </label></th>
					<td class="form-table-data">
					<input type="password" class="input" name="user_password2" id="pass2" required />
					</td>
				</tr>
				<tr>
					<th class="form-table-headline"><label for="nickname">
							ニックネーム(表示名)</label></th>
					<td class="form-table-data">
					<input type="text" class="input" name="user_nickname" id="nickname" required />
					</td>
				</tr>
			</table>
			<div class="">
				<button class="form-button" type="submit">登録</button>
			</div>
		</form>
	</section>
</body>
</html>
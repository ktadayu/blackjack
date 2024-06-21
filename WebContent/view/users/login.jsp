<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Login</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/users/style/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@100..900&display=swap"
	rel="stylesheet">
</head>

<%
String msg = (String) request.getAttribute("message");
%>

<body>

<jsp:include page="../components/nav-top.jsp" />

<section class="global-section">

	<section class="section">
		<h1 class="section-headline">ログイン</h1>
			<%
				if (msg != null) {
			%>
			<h3 class="alert"><%=msg%></h3>
			<%
				}
			%>
		<form action="<%=request.getContextPath()%>/LoginServlet"
			method="post" class="form">
			<table class="form-table">
				<tr>
					<td class="form-table-data">
					<input type="text" class="input" name="user_name" id="id" placeholder="ユーザー名" />
					</td>
				</tr>
				<tr>
					<td class="form-table-data">
					<input type="password" class="input" name="user_password" id="pass" placeholder="パスワード" />
					</td>
				</tr>
			</table>
			<div>
				<button class="form-button" type="submit">ログインする</button>
			</div>
		</form>
		<!-- 登録を促す画面 -->
		<div>
			<h4>-----または-----</h4>
			<!-- 線をcssで作る -->
			<button type="submit" class="form-button" value="signUp"
				onclick="location.href='<%=request.getContextPath()%>/view/users/sign_up.jsp'">登録
			</button>
		</div>
	</section>
</section>
</body>
</html>
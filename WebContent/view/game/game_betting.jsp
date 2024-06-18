<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Black Jack</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/game/style/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@100..900&display=swap"
	rel="stylesheet">

<%
	String imgFileRootName = request.getContextPath() + "/img/tips/";
%>

</head>
<jsp:include page="../components/nav-back-to-top.jsp" />
<body>

	<div class="betting">
		<h1 class="betting-title">betting</h1>
		<div class="grid-area">
			<div class="pos-a" id="tip-1">
				<img src="<%=imgFileRootName%>1.png" alt="1チップ">
			</div>
			<div class="pos-d" id="tip-10">
				<img src="<%=imgFileRootName%>10.png" alt="10チップ">
			</div>
			<div class="pos-e" id="tip-5">
				<img src="<%=imgFileRootName%>5.png" alt="5チップ">
			</div>
			<div class="pos-f"></div>
		</div>

		<div class="div-betting-form">
			<form class="betting-form"
				action="<%=request.getContextPath()%>/BJServlet" method="post">
				<input class="input" type="text" value="0" id="betting-form"
					name="betPoint">
				<button type="submit" class="betting-form-button">bet</button>
			</form>
			<button class="reset-button" id="reset-button">Reset</button>
		</div>
	</div>
	<script src="<%=request.getContextPath()%>/view/game/app/game.js">

	</script>
</body>
</html>
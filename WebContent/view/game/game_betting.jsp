<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
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
<body>
<jsp:include page="../components/nav-back-to-top.jsp" />

	<diV class="transitional-animation">
	</diV>

	<div class="betting">
	<div class="betting-container">
		<h1>betするチップ枚数を選んでください</h1>
		<div class="grid-area">
			<div class="pos-a" id="tip-1">
				<img src="<%=imgFileRootName%>1.png" alt="1チップ">
			</div>
			<div class="pos-e" id="tip-5">
				<img src="<%=imgFileRootName%>5.png" alt="5チップ">
			</div>
			<div class="pos-d" id="tip-10">
				<img src="<%=imgFileRootName%>10.png" alt="10チップ">
			</div>
			<div class="pos-f"></div>
		</div>

		<div class="betting-form-container">
			<form action="<%=request.getContextPath()%>/BJServlet" method="post">
			 <div class="betting-form">
				<input class="input" type="text" value="0" id="betting-form"
					name="betPoint">
			 </div>
				<button class="form-button" type="submit">bet</button>
			<button type="reset" class="form-button" id="reset-button">Reset</button>
			</form>
		</div>
		</div>
	</div>




	<script src="<%=request.getContextPath()%>/view/game/app/game.js"></script>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Black Jack</title>
</head>
<body>
<section class="header-section">
<div class="header">
<a href="<%=request.getContextPath() %>/view/game/game_top.jsp">ゲームトップへ戻る</a>
</div>
</section>

<form class="game_start_form"><!-- formかaにするかで迷う -->
	<div class="div_game_start_button" >
		<button class="game_start_button" type="submit" > ゲームをはじめる(ディール？)</button>
	</div>
</form>

<div class="betting">
<h1>betting処理</h1>
(できればゲーム画面と同一にしたい)
<form class="form-betting" method="get" action="<%= request.getContextPath()%>/BlackJackServlet">
<button class="form-betting-button"> bet </button>
</form>
</div>


</body>
</html>
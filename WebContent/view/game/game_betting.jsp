<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Black Jack</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/view/game/style/style.css">

</head>
<body>
<nav class="nav-header">
    <div class="header">
        <a href="<%=request.getContextPath() %>/view/game/game_top.jsp">ゲームトップへ戻る</a>
    </div>
</nav>

<div class="betting">
<h1 class="betting-title">betting処理</h1>
    <div class="grid-area">
      <div class="pos-a" id ="tip-1" >
          <img src="<%= request.getContextPath() %>/img/tips/1.png" alt="1チップ">
      </div>
      <div class="pos-b">
        <button class="reset-button" id="reset-button"> Reset </button>
      </div>
      <div class="pos-c">

          <span class="em" id="betting-counter"> 0 </span>
      </div>
      <div class="pos-d" id="tip-10">
          <img src="<%= request.getContextPath() %>/img/tips/10.png" alt="10チップ" >
      </div>
      <div class="pos-e" id="tip-5">
          <img src="<%= request.getContextPath() %>/img/tips/5.png" alt="5チップ" >
      </div>
      <div class="pos-f"></div>
    </div>
    <div class="div-betting-form">

    <form class="betting-form" action="<%= request.getContextPath()%>/BJServlet" method="post" >
    	<input type="text" value="0" id="betting-form" name="betPoint">
    	<button type="submit" class="betting-form-button" >bet</button>
    </form>


	</div>
</div>
<script src="<%= request.getContextPath() %>/view/game/app/game.js">
</script>
</body>
</html>
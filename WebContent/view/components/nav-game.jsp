<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ゲームプレイ中のnav</title>
</head>
<link rel="stylesheet" href="<%=request.getContextPath() %>/view/game/style/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@100..900&display=swap" rel="stylesheet">
<body>

<nav class="nav-header">
    <div class="header">
        <a href="<%= request.getContextPath() %>/ToGameTopServlet">ゲームトップへ戻る</a>
    </div>
    <%String message = (String) request.getAttribute("message"); %>
	<%if(message != null){ %>
	<div>
	<%=message %>
	</div>
	<%} %>
</nav>

</body>
</html>
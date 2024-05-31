<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ゲームプレイ中のnav</title>
</head>
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
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.User"%>
<%@ page import="model.History"%>
<%@ page import="dao.UserDao"%>
<%@ page import="dao.HistoryDao"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>戦績</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/view/users/style/style2.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@100..900&display=swap"
	rel="stylesheet">
<%
	User USER = (User) session.getAttribute("USER");
	HistoryDao historyDao = new HistoryDao();
	List<History> historyList = historyDao.selectAllHistory(USER);
%>
</head>
<body>
<jsp:include page="../components/nav-mypage.jsp" />
	<div>
		<table>
			<tr>
				<th class="table-header">No</th>
				<th class="table-header">チップ増減</th>
				<th class="table-header">日時</th>
			</tr>
			<%
				int i = 1;
			%>
			<%
				for (History history : historyList) {
			%>
			<tr>
				<th><%=i%></th>
				<th><%=history.getAmountOfChanges()%></th>
				<th><%=history.getTimeStamp()%></th>
			</tr>
			<%
				i++;
			%>
			<%
				}
			%>
		</table>
	</div>

</body>
</html>
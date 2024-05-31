<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<% User USER = (User) session.getAttribute("USER"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ユーザーの削除</title>
</head>
<jsp:include page="../components/nav-game.jsp"/>
<jsp:include page="../components/nav-top.jsp"/>
<body>
       <section class="section">
              <h1 class="section-headline">ユーザーの削除</h1>
        		<div class="header-notice">
        		<% String msg = (String) request.getAttribute("message");%>
        		<% if(msg!=null){ %>
        		<p><%= msg %></p>
        		<%}else{} %>
        		</div>
              <form action="<%= request.getContextPath() %>/LeaveServlet" method="post" class="form">
                <table class="form-table" >
                <tr>
                <th class="form-table-headline"><label for="id">ニックネーム（自動入力）</label></th>
                <td class="form-table-data">
        			<span class="form-table-user-nickname"><%= USER.getUserNickname() %></span>
                  </td>
                </tr>
                <tr>
                    <th class="form-table-headline"> <label for="pass"> パスワード </label></th>
                    <td class="form-table-data">
                      <input type="text" class="input" name="user_password" id="pass"/>
                    </td>
                </tr>
                </table>
                <div class="form-button">
                  <button class="button button-submission" type="submit">
                   	このユーザーを削除する
                  </button>
                </div>
              </form>
         </section>
</body>
</html>
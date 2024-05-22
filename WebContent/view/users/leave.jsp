<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>退会</title>
</head>
<body>
       <section class="section">
              <h1 class="section-headline">ユーザーの削除</h1>

              <form action="<%= request.getContextPath() %>/LeaveServlet" method="post" class="form">
                <table class="form-table" >
                <tr>
                <th class="form-table-headline"><label for="id">ユーザー名（自動入力）</label></th>
                <td class="form-table-data">
                      <input type="text" class="input" name="user_name" id="id"/>
        			<% //jspでsessionからuser名を取得し入力しておく %>
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
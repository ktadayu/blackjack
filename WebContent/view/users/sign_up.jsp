<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録画面</title>
</head>
<body>


       <section class="section">
              <h1 class="section-headline">登録</h1>

              <form action="<%= request.getContextPath() %>/HelloServlet" method="post" class="form">
                <table class="form-table" >
                <tr>
                <th class="form-table-headline"><label for="id">ユーザー名</label></th>
                <td class="form-table-data">
                      <input type="text" class="input" name="user_name" id="id"/>
                  </td>
                </tr>
                <tr>
                    <th class="form-table-headline"> <label for="pass"> パスワード </label></th>
                    <td class="form-table-data">
                      <input type="text" class="input" name="user_password1" id="pass"/>
                    </td>
        		<% //サーブレット側でパスワードの一致を確認する %>
                </tr>
                <tr>
                    <th class="form-table-headline"> <label for="pass"> パスワード(確認用) </label></th>
                    <td class="form-table-data">
                      <input type="text" class="input" name="user_password2" id="pass"/>
                    </td>
                </tr>
                </table>
                <div class="form-button">
                  <button class="button button-submission" type="submit">
                   	登録
                  </button>
                </div>
              </form>
         </section>

</body>
</html>
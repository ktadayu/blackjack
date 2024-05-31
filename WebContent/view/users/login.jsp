<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<jsp:include page="../components/nav.jsp"/>
<body>
       <section class="section">
              <h1 class="section-headline">ログイン</h1>
              <form action="<%= request.getContextPath() %>/LoginServlet" method="post" class="form">
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
                      <input type="text" class="input" name="user_password" id="pass"/>
                    </td>
                </tr>
                </table>
                <div class="form-button">
                  <button class="button button-submission" type="submit">
                   	ログインする
                  </button>
                </div>
              </form>
         </section>
        <!-- 登録を促す画面 -->
        <section class="section">
                <div class="form-button">
                <h4>-----または-----</h4><!-- 線をcssで作る -->
                <button type="submit" class="button button-signin" onclick="location.href='<%= request.getContextPath()%>/view/users/sign_up.jsp'" >登録 </button>
                </div>
        </section>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/view/users/style/style.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@100..900&display=swap" rel="stylesheet">
</head>
<body>
       <section class="section">
              <h1 class="section-headline">ログイン</h1>
              <form action="<%= request.getContextPath() %>/LoginServlet" method="post" class="form">
                <table class="form-table" >
                <tr>
                <td class="form-table-data">
                      <input type="text" class="input" name="user_name" id="id" placeholder="ユーザー名"/>
                  </td>
                </tr>
                <tr>
                    <td class="form-table-data">
                      <input type="password" class="input" name="user_password" id="pass" placeholder="パスワード"/>
                    </td>
                </tr>
                </table>
                <div class="form-button">
                  <button class="button button-submission" type="submit">
                   	ログインする
                  </button>
                </div>
              </form>
        <!-- 登録を促す画面 -->
                <div class="form-button">
                <h4>-----または-----</h4><!-- 線をcssで作る -->
                <button type="submit" class="button button-signin" onclick="location.href='<%= request.getContextPath()%>/view/users/sign_up.jsp'" >登録 </button>
                </div>
        </section>
</body>
</html>
package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import exception.MyException;
import model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    //ログアウトメソッド
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		/*
		User user = (User) session.getAttribute("USER");
		System.out.println(user.getUserNickname()); //（ログインしているユーザーのニックネーム）
		*/
		session.invalidate();
		/* セッションが無効化されているため以下ちゃんとエラーになる:
		User user2 = (User) session.getAttribute("USER");
		System.out.println(user2.getUserNickname());
		 */
		request.setAttribute("message", "ログアウトしました");
		String s = "/view/users/login.jsp";

		RequestDispatcher rd = request.getRequestDispatcher(s);
		rd.forward(request, response);
	}

	//ログインメソッド
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String user_name = request.getParameter("user_name");
		String user_password = request.getParameter("user_password");
		String nextPage = null;
		try {

			UserDao userDao = new UserDao();
			User user = userDao.doLogin(user_name, user_password);

			//sessionにuser型インスタンスを格納
			//ログアウト時にinvalidateにする予定
			HttpSession session = request.getSession();
			session.setAttribute("USER", user);

			// ContentServletのPOSTに移動
			//nextPage = "ContentServlet";
			nextPage ="/view/game/game_top.jsp";
		} catch (MyException e) {
			/*UserDaoで、
			 * throw new MyException("ユーザー名・パスワードのいずれかに誤りがあります。");
			 * として新規例外を作成して投げている　
			 */
			String message = e.getMessage();
			request.setAttribute("message", message);
			request.setAttribute("error", "true");

			// ログイン画面へ
			nextPage = "/view/users/login.jsp";
		}

		// 次の画面に遷移
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}

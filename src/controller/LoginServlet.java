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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		session.invalidate();

		request.setAttribute("message", "ログアウトしました");
		String next = "/view/users/login.jsp";

		RequestDispatcher rd = request.getRequestDispatcher(next);
		rd.forward(request, response);
	}

	//ログインメソッド
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String user_name = request.getParameter("user_name");
		String user_password = request.getParameter("user_password");
		String nextPage = null;
		try {
			UserDao userDao = new UserDao();
			User user = userDao.doLogin(user_name, user_password);

			//sessionにuser型インスタンスを格納
			HttpSession session = request.getSession();
			session.setAttribute("USER", user);

			nextPage = "ToGameTopServlet";

		} catch (MyException e) {

			String message = e.getMessage();
			request.setAttribute("message", message);

			// ログイン画面へ
			nextPage = "/view/users/login.jsp";
		}

		// 次の画面に遷移
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}

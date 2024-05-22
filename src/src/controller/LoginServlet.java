package src.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDao;
import exception.MyException;
import model.User;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	//ログイン
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int user_id = Integer.parseInt(request.getParameter("user_id"));
		String user_password = request.getParameter("user_password");
		String nextPage = null;
		try {

			UserDao userDao = new AccountDao();
			User user = userDao.doLogin(user_id, user_password);

			//sessionにインスタンスを格納
			//ログアウト時にinvalidateにする
			HttpSession session = request.getSession();
			session.setAttribute("USER", user);

			// ContentServletのPOSTに移動
			//nextPage = "ContentServlet";
			nextPage ="ContentServlet";
		} catch (MyException e) {

			String message = e.getMessage();
			request.setAttribute("message", message);
			request.setAttribute("error", "true");

			// ログイン画面へ
			nextPage = "/view/login.jsp";
		}

		// 次の画面に遷移
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}

package controller;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	//ユーザーのリストを取得する
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nextPage = null;

		HttpSession session = request.getSession();
		User myUser = (User) session.getAttribute("USER");

		if (myUser.getUserName().equals("admin")) {

			try {
				UserDao userDao = new UserDao();
				List<User> users = userDao.selectAllUsers();
				request.setAttribute("USERLIST", users);
				nextPage = "/view/users/list.jsp";
			} catch (MyException e) {
				e.getMessage();
				nextPage = "/view/users/login.jsp";
			}
		} else {
			String msg = "管理者権限がありません";
			String status = "true";

			request.setAttribute("message", msg);
			request.setAttribute("error", status);

			nextPage = "ToGameTopServlet";
		}
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

}

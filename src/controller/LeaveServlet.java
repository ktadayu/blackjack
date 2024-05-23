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


@WebServlet("/LeaveServlet")
public class LeaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public LeaveServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		User USER = (User) session.getAttribute("USER");

//		String user_nickname = USER.getUserNickname();
//		System.out.println(user_nickname);

		String inputPassword = request.getParameter("user_password");

		String nextPage = null;

		if(inputPassword.equals(USER.getUserPassword())) {
			System.out.println("正しいパスワードです");
			try {
			UserDao userDao = new UserDao();
			userDao.doDelete(USER.getUserName());

			String message = "ユーザーを削除しました";
			request.setAttribute("message", message);
			nextPage = "/view/users/login.jsp";

			}catch(MyException e) {
				String message = e.getMessage();
				request.setAttribute("message", message);
			}

		}else {
			String message = "パスワードが間違っています。";
			request.setAttribute("message", message);
			nextPage = "/view/users/leave.jsp";
		}

		// 次の画面に遷移
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);

	}

}

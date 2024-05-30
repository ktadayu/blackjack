package garbage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blackjack.Hand;
import blackjack.players.Player;
import dao.UserDao;
import exception.MyException;
import model.User;


@WebServlet("/CheckBlackJack")
public class CheckBlackJack extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public CheckBlackJack() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		User user = (User) session.getAttribute("USER");
		Player player =(Player) session.getAttribute("PLAYER");
		Integer betPoint = (Integer) session.getAttribute("BETPOINT");

		//画面遷移先の設定
		String nextPage = "/view/game/game_playing.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);

		//手札の取得
		Hand playerHand = player.getHand();

		Boolean dic = true;//true:ゲーム中 false:ゲーム終了
		request.setAttribute("dic", dic);

		if(playerHand.totalValue() == 21 && (playerHand.getCards().get(0).getCardNumber() == 1 || playerHand.getCards().get(1).getCardNumber() == 1  )) {
			String e = "ブラックジャック！";
			user.setNumberOfTips((int) (user.getNumberOfTips() + (2.5)*betPoint));
			addHistory(user,+betPoint,request);
			updateStatus(user,request);
			dic = false;
			request.setAttribute("msg", e); request.setAttribute("dic", dic);
			requestDispatcher.forward(request, response);
			return ;
		}

		requestDispatcher.forward(request, response);
	}

	//DBのuserへ現在のチップ数を反映させるメソッド
	public void updateStatus(User user , HttpServletRequest request) {

		try {
		UserDao userDao = new UserDao();
		userDao.updateNumberOfTips(user);
		}catch(MyException e) {
			String message = e.getMessage();
			request.setAttribute("message", message);
		}finally {

		}
	}

	//DBで勝敗を記録する
	public void addHistory(User user, int amountOfChange ,HttpServletRequest request) {

		try {
		UserDao userDao = new UserDao();
		userDao.addToHistory(user,amountOfChange);
		}catch(MyException e) {
			String message = e.getMessage();
			request.setAttribute("message", message);
		}finally {

		}
	}

}

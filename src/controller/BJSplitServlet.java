package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blackjack.Deck;
import blackjack.Hand;
import blackjack.players.Dealer;
import blackjack.players.Player;
import dao.HistoryDao;
import dao.UserDao;
import exception.MyException;
import model.User;

@WebServlet("/BJSplitServlet")
public class BJSplitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public BJSplitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		Deck deck = (Deck) session.getAttribute("DECK");
		Player player = (Player) session.getAttribute("PLAYER");
		Hand playerHand = player.getHand();

		Player player1 = new Player();
		Player player2 = new Player();

		player1.addCard(playerHand.get(0));
		player2.addCard(playerHand.get(1));
		player1.addCard(deck.deal());
		player2.addCard(deck.deal());

		//セッションに登録
		session.setAttribute("plyr1", player1);
		session.setAttribute("plyr2", player2);

		String nextPage = "/view/game/play.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);

		requestDispatcher.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//画面遷移先の設定
		String nextPage = "/view/game/play.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);

		//ゲーム要素取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("USER");
		Deck deck = (Deck) session.getAttribute("DECK");
		Dealer dealer = (Dealer) session.getAttribute("DEALER");
		Integer betPoint = (Integer) session.getAttribute("BETPOINT");
		Player player1 = (Player) session.getAttribute("plyr1");
		Player player2 = (Player) session.getAttribute("plyr2");

		String opt1 = request.getParameter("opt1");
		String opt2 = request.getParameter("opt2");

		//手札1についての選択
		//standを選ぶorバーストでendFlag(n)属性をsessionに登録
		if(opt1 != null && opt1.equals("hit")) {
		    player1.drawCard(deck);
		    if (player1.getHand().isBust()) {
				request.setAttribute("msg1", "手札1バースト!");
				session.setAttribute("endFlag1", true);
				updateStatus(user, -betPoint, request);
		    }
			requestDispatcher.forward(request, response);
			return;
		}

		if(opt1 != null && opt1.equals("stand")) {
			session.setAttribute("endFlag1", true);
		}

		//手札2についての選択

		if(opt2 != null && opt2.equals("hit")) {
		    player2.drawCard(deck);
		    if (player2.getHand().isBust()) {
				request.setAttribute("msg2", "手札2バースト!");
				session.setAttribute("endFlag2", true);
				updateStatus(user, -betPoint, request);
		    }

			requestDispatcher.forward(request, response);
			return;
		}

		if(opt2 != null && opt2.equals("stand")) {
			session.setAttribute("endFlag2", true);
		}








	}
		//DBのuserへ現在のチップ数を反映させるメソッド
		public void updateStatus(User user, int amountOfChange, HttpServletRequest request) {
			try {
				UserDao userDao = new UserDao();
				userDao.updateNumberOfTips(user); //DBのユーザーテーブルのチップ数を更新
				HistoryDao historyDao = new HistoryDao();
				historyDao.addToHistory(user, amountOfChange); //DBの戦績を登録
			} catch (MyException e) {
				String message = e.getMessage();
				request.setAttribute("message", message);
			}
		}


}

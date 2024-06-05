package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blackjack.BJLogic;
import blackjack.Deck;
import blackjack.Hand;
import blackjack.players.Dealer;
import blackjack.players.Player;
import dao.HistoryDao;
import dao.UserDao;
import exception.MyException;
import model.FlagOwner;
import model.User;

@WebServlet("/BJSplitServlet")
public class BJSplitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BJSplitServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	//スプリット選択後ここへ遷移
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		Deck deck = (Deck) session.getAttribute("DECK");
		Player player = (Player) session.getAttribute("PLAYER");
		Hand playerHand = player.getHand();

		FlagOwner.validateUsualGameEnd();//通常のゲームの終了を宣言
		FlagOwner.validateSplit(); //split中を宣言

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

	//スプリットしたゲームでのサーブレット
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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
		if (opt1 != null && opt1.equals("hit")) {
			player1.drawCard(deck);

			if (player1.getHand().isBust()) {
				request.setAttribute("msg1", "手札1バースト!");
				FlagOwner.validatePlayer1(); //プレイヤー1終了
				updateStatus(user, -betPoint, request);
			}else if(player1.getHand().totalValue() ==21 ) {
				FlagOwner.validatePlayer1();
			}
		}

		if (opt1 != null && opt1.equals("stand")) {
			FlagOwner.validatePlayer1(); //プレイヤー1終了
		}

		//手札2についての選択
		if (opt2 != null && opt2.equals("hit")) {
			player2.drawCard(deck);
			if (player2.getHand().isBust()) {
				request.setAttribute("msg2", "手札2バースト!");
				FlagOwner.validatePlayer2(); //プレイヤー2終了
				updateStatus(user, -betPoint, request);
			}else if(player2.getHand().totalValue() ==21 ) {
				FlagOwner.validatePlayer2();
			}
		}

		if (opt2 != null && opt2.equals("stand")) {
			FlagOwner.validatePlayer2(); //プレイヤー1終了
		}

		//ど手札選択が2つともendでない限りページに戻される
		if (!FlagOwner.checkPlayer1End() || !FlagOwner.checkPlayer2End()) {
			requestDispatcher.forward(request, response);
			return;
		}

		//両方バーストだった場合にディーラードローをスキップしたい
		if (!player1.getHand().isBust() || !player2.getHand().isBust()) {
			dealer.drawCard(deck);
		}


		//勝敗
		//手札1について
		if (BJLogic.detWinner(player1.getHand(), dealer.getHand()) == 1) {
			user.setNumberOfTips(user.getNumberOfTips() + 2 * betPoint);
			updateStatus(user, +betPoint, request);
		} else if (BJLogic.detWinner(player1.getHand(), dealer.getHand()) == 0) {
			user.setNumberOfTips(user.getNumberOfTips() + betPoint);
			updateStatus(user, 0, request);
		} else {
			updateStatus(user, -betPoint, request);
		}
		//msg1に勝敗を登録
		request.setAttribute("msg1", BJLogic.msg);


		//手札2について
		if (BJLogic.detWinner(player2.getHand(), dealer.getHand()) == 1) {
			user.setNumberOfTips(user.getNumberOfTips() + 2 * betPoint);
			updateStatus(user, +betPoint, request);
		} else if (BJLogic.detWinner(player2.getHand(), dealer.getHand()) == 0) {
			user.setNumberOfTips(user.getNumberOfTips() + betPoint);
			updateStatus(user, 0, request);
		} else {
			updateStatus(user, -betPoint, request);
		}
		//msg2に勝敗を登録
		request.setAttribute("msg2", BJLogic.msg);

		session.setAttribute("USER", user);
		FlagOwner.validateGameEnd();
		requestDispatcher.forward(request, response);

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
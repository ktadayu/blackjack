package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blackjack.BJTable;
import blackjack.Deck;
import blackjack.Hand;
import blackjack.players.Dealer;
import blackjack.players.Player;
import dao.HistoryDao;
import dao.UserDao;
import exception.MyException;
import model.FlagOwner;
import model.User;

@WebServlet("/BJServlet")
public class BJServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//hit or stand, splitの後遷移
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//画面遷移先の設定
		String nextPage = "/view/game/play.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);

		//ゲーム要素取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("USER");
		Deck deck = (Deck) session.getAttribute("DECK");
		Player player = (Player) session.getAttribute("PLAYER");
		Dealer dealer = (Dealer) session.getAttribute("DEALER");
		Integer betPoint = (Integer) session.getAttribute("BETPOINT");

		//hit,standの選択の取得
		String opt = request.getParameter("opt");

		//手札の取得
		Hand playerHand = player.getHand();
		Hand dealerHand = dealer.getHand();

		//BJLogicに書けることかもしれないがとりあえず記述する
		if (opt.equals("split")) {
			//チップの徴収
			user.setNumberOfTips(user.getNumberOfTips() - betPoint);
			insertUserTipIntoDB(user, request);
			//遷移
			nextPage = "/BJSplitServlet";
			requestDispatcher = request.getRequestDispatcher(nextPage);
			requestDispatcher.forward(request, response);
			return;
		}

		//splitが選択されなかったので,splittableをfalseにする
		FlagOwner.inValidateSplittableFlag();

		//プレイヤーの選択による分岐
		if (opt.equals("hit")) {
			player.drawCard(deck);
			if (playerHand.isBust()) {
				request.setAttribute("msg", "バースト!");
				FlagOwner.validateUsualGameEnd();
				updateStatus(user, -betPoint, request);
				requestDispatcher.forward(request, response);
				return;
			} else if (playerHand.totalValue() == 21) {
				FlagOwner.validateUsualGameEnd();
			}
			//再選択
			requestDispatcher.forward(request, response);
			return;
		}

		dealer.drawCard(deck);

		//勝敗
		if (BJTable.detWinner(playerHand, dealerHand) == 1) {
			user.setNumberOfTips(user.getNumberOfTips() + 2 * betPoint);
			updateStatus(user, +betPoint, request);
		} else if (BJTable.detWinner(playerHand, dealerHand) == 0) {
			user.setNumberOfTips(user.getNumberOfTips() + betPoint);
			updateStatus(user, 0, request);
		} else {
			updateStatus(user, -betPoint, request);
		}

		session.setAttribute("USER", user);
		request.setAttribute("msg", BJTable.msg);
		FlagOwner.validateUsualGameEnd();
		requestDispatcher.forward(request, response);
	}

	//ゲームの開始と再プレイ
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int betPoint;
		//初回か再プレイかのフラグ
		boolean firstOrNot = true;
		//スプリット判定やゲーム終了判定用のフラグのリセット
		FlagOwner.resetFlag();

		//セッションの取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("USER");

		//選択されたベット額を取得
		//requestのbetPointがnullかどうかで初回プレイか否かの判定
		if (request.getParameter("betPoint") != null) {
			//初回
			betPoint = Integer.parseInt(request.getParameter("betPoint"));
			session.setAttribute("BETPOINT", betPoint);
		} else {
			firstOrNot = false;
			betPoint = (int) session.getAttribute("BETPOINT");
		}

		//BJ用のテーブル: セッションからユーザーとbetPointを取り出し、ユーザーのチップを徴収,登録
		BJTable bjTable = new BJTable(session);
		insertUserTipIntoDB(user, request);

		//ゲームのスタート
		bjTable.startGame(firstOrNot, session);

		//ナチュラルBJ成立判定
		if ((Boolean) session.getAttribute("BLACKJACK")) {
			updateStatus(user, (int) 2.5 * betPoint, request);
			session.setAttribute("USER", user);
			session.setAttribute("BLACKJACK", false);
			request.setAttribute("msg", "ブラックジャック！");
			//ゲーム終了を宣言
			FlagOwner.validateUsualGameEnd();
		}

		//split可能判定
		if (FlagOwner.checkSplittable()) {
			//スプリット可能を宣言
			FlagOwner.validateSplittableFlag();
		}

		String nextPage = "/view/game/play.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);
	}

	//DBのuserへ現在のチップ数を反映させるメソッド
	public void insertUserTipIntoDB(User user, HttpServletRequest request) {
		try {
			UserDao userDao = new UserDao();
			userDao.updateNumberOfTips(user);
		} catch (MyException e) {
			String message = e.getMessage();
			request.setAttribute("message", message);
		} finally {

		}
	}

	//userオブジェクトのチップ数を更新し、DBのuserへ反映させるメソッド
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
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
		if(opt.equals("split")) {
			//チップの徴収
			user.setNumberOfTips(user.getNumberOfTips() - betPoint);
			//遷移
			nextPage = "/BJSplitServlet";
			requestDispatcher = request.getRequestDispatcher(nextPage);
			requestDispatcher.forward(request, response);
			return;
		}

		//splitが選択されなかったので,splittableをfalseにする
		FlagOwner.unValidateSplittableFlag();

		//プレイヤーの選択による分岐
		if (opt.equals("hit")) {
			player.drawCard(deck);
			if (playerHand.isBust()) {
				request.setAttribute("msg", "バースト!");
				FlagOwner.validateUsualGameEnd();
				updateStatus(user, -betPoint, request);
				requestDispatcher.forward(request, response);
				return;
			} else if(playerHand.totalValue() == 21){
				FlagOwner.validateUsualGameEnd();
			}else{
				//再選択
				requestDispatcher.forward(request, response);
				return;
			}
		}

		dealer.drawCard(deck);

		//勝敗
		if (BJLogic.detWinner(playerHand, dealerHand) == 1) {
			user.setNumberOfTips(user.getNumberOfTips() + 2 * betPoint);
			updateStatus(user, +betPoint, request);
		} else if (BJLogic.detWinner(playerHand, dealerHand) == 0) {
			user.setNumberOfTips(user.getNumberOfTips() + betPoint);
			updateStatus(user, 0, request);
		} else {
			updateStatus(user, -betPoint, request);
		}

		session.setAttribute("USER", user);
		request.setAttribute("msg", BJLogic.msg);
		FlagOwner.validateUsualGameEnd();
		requestDispatcher.forward(request, response);
	}

	//ゲームの開始と再プレイ
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nextPage = "/view/game/play.jsp";
		//初回か再プレイかのフラグ, 実際には不要
		boolean firstTurn = true;
		int betPoint;

		//split等フラグのリセット
		FlagOwner.resetFlag();

		//セッションの取得
		HttpSession session = request.getSession();


		//選択されたベット額を取得
		//requestのbetPointがnullかどうかで初回プレイか否かの判定
		if (request.getParameter("betPoint") != null) {
			//初回
			betPoint = Integer.parseInt(request.getParameter("betPoint"));
			session.setAttribute("BETPOINT", betPoint);
		} else {
			firstTurn = false;
			betPoint = (int) session.getAttribute("BETPOINT");
		}

		//BJロジック: セッションからユーザーとbetPointを取り出し、ユーザーのチップを徴収
		//BJLogicをnewしているのは気持ち悪い
		BJLogic bjLogic = new BJLogic(session);

		//ゲームの初期化 or リプレイ(デッキとbet額を変えずにプレイ)
		if (firstTurn) {
			session = bjLogic.initializeBJ(session);
		} else {
			session = bjLogic.ReplayBJ(session);
		}

		//ナチュラルBJ成立の場合
		if ((Boolean) session.getAttribute("BLACKJACK") != null) {
			session.removeAttribute("BLACKJACK");
			User user = (User) session.getAttribute("USER");
			updateStatus(user, (int) 2.5 * betPoint, request);
			session.setAttribute("USER", user);
			request.setAttribute("msg", "ブラックジャック！");
			FlagOwner.validateUsualGameEnd();
		}

		//split可能かどうか？
		if(FlagOwner.checkSplittable()) {
			FlagOwner.validateSplittableFlag();
		}

		//ゲーム初回のフラグ
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
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
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
import model.User;

/**
 * Servlet implementation class BJServlet
 */
@WebServlet("/BJServlet")
public class BJServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public BJServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

//hit or standの後遷移
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//画面遷移先の設定
		String nextPage = "/view/game/play.jsp";
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("USER");
		Deck deck = (Deck) session.getAttribute("DECK");
		Player player =(Player) session.getAttribute("PLAYER");
		Dealer dealer = (Dealer) session.getAttribute("DEALER");
		Integer betPoint = (Integer) session.getAttribute("BETPOINT");

		//hit,standの選択の取得
		String opt = request.getParameter("opt");

		//手札の取得
		Hand playerHand = player.getHand();
		Hand dealerHand = dealer.getHand();

		Boolean dic = true;//true:ゲーム中 false:ゲーム終了
		request.setAttribute("dic", dic);

		//プレイヤーの選択による分岐
		if(opt.equals("stand")) {
			 dic = false;
			//dealerのターン
			dealer.drawCard(deck);
		}else{
			player.drawCard(deck);
			if(playerHand.isBust()) {
				String e = "プレイヤーの負けです";
				updateStatus(user,-betPoint,request);
				dic = false;
				request.setAttribute("msg", e);
				request.setAttribute("dic", dic);
				requestDispatcher.forward(request, response);
				return ;
			}else if(playerHand.totalValue() == 21){
				dic = false ;
				dealer.drawCard(deck);
			}else{//再選択
				requestDispatcher.forward(request, response);
				return ;
			}
		}


		//勝敗
		String e;

		 if(playerHand.totalValue() > dealerHand.totalValue() || dealerHand.totalValue() > 21){
				 e = "プレイヤーの勝利";
				 user.setNumberOfTips(user.getNumberOfTips() + 2*betPoint);
				 updateStatus(user,+betPoint,request);
			}else if(playerHand.totalValue() == dealerHand.totalValue()){
				 e = "引き分け";
				 user.setNumberOfTips(user.getNumberOfTips() + betPoint);
				 addHistory(user,0,request);
			}else {
				 e = "ディーラーの勝利";
				 updateStatus(user,-betPoint,request);
			}

		 	session.setAttribute("USER", user);
			request.setAttribute("msg", e);
			request.setAttribute("dic", false);
		requestDispatcher.forward(request, response);
	}

	//ゲームの開始と再プレイ
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nextPage = "/view/game/play.jsp";
		//初回か再プレイかのフラグ, 実際には不要
		boolean thisIsFirstTurn = true;
		int betPoint ;

		//セッションの取得
		HttpSession session = request.getSession();

		//選択されたベット額を取得
		//requestのbetPointがnullかどうかで初回プレイか否かの判定
		if(request.getParameter("betPoint") != null) {
			betPoint = Integer.parseInt(request.getParameter("betPoint"));
			session.setAttribute("BETPOINT", betPoint);
		}else {
			thisIsFirstTurn = false;
			betPoint = (int) session.getAttribute("BETPOINT");
		}

		//セッションからユーザーとbetPointを取り出し、ユーザーのチップを徴収
		BJLogic bjLogic = new BJLogic(session);

		//ゲームの初期化↓ or リプレイ
		//デッキ、プレイヤー、ディーラーの生成
		//デッキのシャッフル、手札配布、ナチュラルBJ判定
		//return HttpSession
		if(thisIsFirstTurn == true) {
		session = bjLogic.initializeBJ(session);
		}else {
		session = bjLogic.ReplayBJ(session);
		}

		//ナチュラルBJ成立の場合
		if((Boolean) session.getAttribute("BLACKJACK") != null) {
			session.removeAttribute("BLACKJACK");
			User user = (User) session.getAttribute("USER");
			updateStatus(user,(int) 2.5 * betPoint,request);
			session.setAttribute("USER", user);
			request.setAttribute("msg", "ブラックジャック！"); request.setAttribute("dic", false);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
			requestDispatcher.forward(request, response);
			return ;
		}

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);

	}

	//DBのuserへ現在のチップ数を反映させるメソッド
	public void updateStatus(User user , int amountOfChange,HttpServletRequest request) {
		try {
		UserDao userDao = new UserDao();
		userDao.updateNumberOfTips(user); //DBのユーザーテーブルのチップ数を更新
		HistoryDao historyDao = new HistoryDao();
		historyDao.addToHistory(user,amountOfChange); //DBの戦績を登録
		}catch(MyException e) {
			String message = e.getMessage();
			request.setAttribute("message", message);
		}finally {
		}
	}

	//DBで勝敗を記録する
	public void addHistory(User user, int amountOfChange ,HttpServletRequest request) {
		try {
		HistoryDao historyDao = new HistoryDao();
		historyDao.addToHistory(user,amountOfChange);
		}catch(MyException e) {
			String message = e.getMessage();
			request.setAttribute("message", message);
		}finally {
		}
	}

}

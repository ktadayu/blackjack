//package controller;
//
//import java.io.IOException;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import blackjack.Deck;
//import blackjack.players.Dealer;
//import blackjack.players.Player;
//import model.User;
//
//
//@WebServlet("/PlayingServlet")
//public class PlayingServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//
//    public PlayingServlet() {
//        super();
//    }
//
////NewGame with the same bets
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		HttpSession session = request.getSession();
//
//		Deck deck = (Deck) session.getAttribute("DECK");
//		Player player =(Player) session.getAttribute("PLAYER");
//		Dealer dealer = (Dealer) session.getAttribute("DEALER");
//		Integer betPoint = (Integer) session.getAttribute("BETPOINT");
//		User user = (User) session.getAttribute("USER");
//
//		//bet処理
//		user.setNumberOfTips(user.getNumberOfTips() - betPoint);
//		session.setAttribute("BETPOINT", betPoint);
//
//		//デッキ枚数が減ったらデッキの初期化
//		if(deck.size()<20) {
//			deck = new Deck();
//			deck.deckShuffle();
//			session.setAttribute("DECK", deck);
//		}
//
//		//手札を初期化
//		player.removeHand();
//		dealer.removeHand();
//		drawCard(player,dealer,deck);
//
//		//ゲームの段階判定 初期値設定
//		request.setAttribute("dic", true);
//
//		String nextPage = "CheckBlackJack";
//
//		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
//		requestDispatcher.forward(request, response);
//
//	}
//
////ゲーム開始
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//		HttpSession session = request.getSession();
//		Deck deck = (Deck) session.getAttribute("DECK");
//		Player player =(Player) session.getAttribute("PLAYER");
//		Dealer dealer = (Dealer) session.getAttribute("DEALER");
//
//		//山札シャッフル
//		deck.deckShuffle();
//		//初期手札配布
//		drawCard(player,dealer,deck);
//
//		String nextPage = "CheckBlackJack";
//		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
//		requestDispatcher.forward(request, response);
//	}
//
//	public void drawCard(Player player, Dealer dealer,Deck deck) {
//		player.addCard(deck.deal());
//		player.addCard(deck.deal());
//		dealer.addCard(deck.deal());
//		dealer.addCard(deck.deal());
//	}
//
//}

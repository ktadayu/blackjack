package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blackjack.cal.Deck;
import blackjack.players.Dealer;
import blackjack.players.Player;


@WebServlet("/PlayingServlet")
public class PlayingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public PlayingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		Deck deck = (Deck) session.getAttribute("DECK");
		Player player =(Player) session.getAttribute("PLAYER");
		Dealer dealer = (Dealer) session.getAttribute("DEALER");

		//手札を初期化
		player.removeHand();
		dealer.removeHand();

		//デッキ枚数が減ったら初期化
		if(deck.size()<20) {
			deck = new Deck();
			deck.deckShuffle();
			session.setAttribute("DECK", deck);
		}

		//プレイヤー達にカードを引けと指示
		player.addCard(deck.deal());
		player.addCard(deck.deal());
		dealer.addCard(deck.deal());
		dealer.addCard(deck.deal());

		request.setAttribute("dic", true);

		String nextPage = "/view/game/game_playing.jsp";

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nextPage = "/view/game/game_playing.jsp";

		HttpSession session = request.getSession();

		Deck deck = (Deck) session.getAttribute("DECK");
		Player player =(Player) session.getAttribute("PLAYER");
		Dealer dealer = (Dealer) session.getAttribute("DEALER");

		//デッキのシャッフルの指示
		deck.deckShuffle();

		//プレイヤー達にカードを引けと指示
		player.addCard(deck.deal());
		player.addCard(deck.deal());
		dealer.addCard(deck.deal());
		dealer.addCard(deck.deal());

		request.setAttribute("dic", true);

		System.out.println(deck.size() + "枚(残)");

		RequestDispatcher requestDispatcher = request.getRequestDispatcher(nextPage);
		requestDispatcher.forward(request, response);

	}

}

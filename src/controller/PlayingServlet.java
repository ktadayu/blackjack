package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Deck deck = (Deck) request.getAttribute("DECK");
		Player player =(Player) request.getAttribute("PLAYER");
		Dealer dealer = (Dealer) request.getAttribute("DEALER");



	}

}

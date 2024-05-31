package dao;

import javax.servlet.http.HttpSession;

import blackjack.Deck;
import blackjack.players.Dealer;
import blackjack.players.Player;
import model.User;

public class SessionOwner {

	Deck deck;
	Player player;
	Dealer dealer;
	Integer betPoint ;
	User user ;

//	this.user= user;
//	this.deck = deck;
//	this.player = player;
//	this.dealer = dealer;
//	this.betPoint = betPoint ;

	public SessionOwner getGameElmFrom(HttpSession session) {
		deck = (Deck) session.getAttribute("DECK");
		player =(Player) session.getAttribute("PLAYER");
		dealer = (Dealer) session.getAttribute("DEALER");
		betPoint = (Integer) session.getAttribute("BETPOINT");
		user = (User) session.getAttribute("USER");
		return this;
	}

	public HttpSession setSessionAttribute(HttpSession session, User user, int betPoint, Deck deck, Player player, Dealer dealer) {
		session.setAttribute("USER", user);
		session.setAttribute("DECK", deck);
		session.setAttribute("PLAYER", player);
		session.setAttribute("DEALER", dealer);
		session.setAttribute("BETPOINT",betPoint);
		return session;
	}



}

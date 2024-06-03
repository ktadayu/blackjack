package blackjack.players;

import blackjack.Deck;
import blackjack.Hand;

public class Player extends Base{

	public Player() {
		super();
	}

	public Player(int tips) {
		super();
		this.tips = tips;
	}

	public Hand drawCard(Deck deck) {

		addCard(deck.deal());
		return hand;
	}

}

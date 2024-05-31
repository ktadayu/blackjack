package blackjack.players;

import blackjack.Deck;
import blackjack.Hand;

public class Dealer extends Base{

	//カードを引いて手札に加える処理
	public Hand drawCard(Deck deck) {
		while(hand.totalValue() < 17) {
			addCard(deck.deal());
		}
		return hand;
	}

}

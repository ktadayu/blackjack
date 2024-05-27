package blackjack.players;

import blackjack.cal.Deck;
import blackjack.cal.Hand;

public class Dealer extends Base{

	//カードを引いて手札に加える処理
	public Hand drawCard(Deck deck) {
		while(hand.totalValue() < 17) {
			addCard(deck.deal()); //山札の一番上を引いて
		}
		return hand;
	}

}

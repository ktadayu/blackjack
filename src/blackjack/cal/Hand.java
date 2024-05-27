package blackjack.cal;

import java.util.ArrayList;
import java.util.List;

public class Hand {

	private List<Card> cards;

	public Hand() {
		cards = new ArrayList<>();
	}

	public List<Card> getCards() {
		return this.cards;
	}

	//手札の合計値を計算
	public int totalValue() {
		int sum = 0;
		for(Card card : cards) {
			sum += card.getValue();
		}
		return sum;
	}

}

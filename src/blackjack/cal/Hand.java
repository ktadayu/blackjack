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

		//手札の1以外のカードの和を計算
		int sum = 0;
		int aceCount = 0;
		for(Card card : cards) {
			if(card.getCardNumber() != 1) {
			sum += card.getValue();
			}else {
				aceCount++ ;
			}
		}

		if(aceCount > 0 && sum + aceCount <= 11) {
			return sum + (10+aceCount);
		}else {
			return sum +  aceCount ;
		}

	}

}

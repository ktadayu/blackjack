package blackjack;

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

	public int size() {
		return cards.size();
	}

	public Card get(int i) {
		return cards.get(i);
	}

	public Boolean isEmpty() {
		return cards.isEmpty();
	}

	//手札の合計値を計算
	public int totalValue() {

		int sum = 0;
		int aceCount = 0;
		//手札の1以外のカードの和を計算
		for (Card card : cards) {
			if (card.getCardNumber() != 1) {
				sum += card.getValue();
			} else {
				aceCount++;
			}
		}

		if (aceCount > 0 && sum + aceCount <= 11) {
			return sum + (10 + aceCount);
		} else {
			return sum + aceCount;
		}
	}

	public Boolean isBust() {
		if (this.totalValue() > 21) {
			return true;
		}
		return false;
	}

	public Boolean isSplitable() {
		if(this.size()==2 && this.get(0).getCardNumber() == this.get(1).getCardNumber()) {
			return true;
		}
		return false;
	}

}

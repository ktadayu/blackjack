package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blackjack.Card.Mark;

public class Deck {

	List<Card> cards;

	//コンストラクタでデッキ(List)の生成
	public Deck() {
		cards = new ArrayList<>();
		for (Mark mark : Mark.values()) {
			for (int i = 1; i <= 13; i++) {
				this.cards.add(new Card(mark, i));
			}
		}
	}

	public List<Card> deckToTestSplit() {
		List<Card> newCards = new ArrayList<>();
			for(int i = 1 ; i <=13; i++) {
				for(Mark mark : Mark.values()) {
					newCards.add(new Card(mark,i));
				}
			}
			cards = newCards;
			return cards;
	}

	//デッキをシャフル
	public List<Card> deckShuffle() {
		Collections.shuffle(cards);
		System.out.println(cards);
		return cards;
	}

	//山札の一番上を取得した後消去
	public Card deal() {
		Card card = cards.get(0);
		cards.remove(0);
		return card;
	}

	public int size() {
		return cards.size();
	}

}

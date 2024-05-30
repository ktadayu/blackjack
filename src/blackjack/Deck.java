package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import blackjack.Card.Mark;

public class Deck {

	List<Card> cards;

	//デッキの実態？をListで作成
	//Listの初期値をトランプ全カード
	//イメージとしては[(s,1),(s,2)...(h,1)(h,2)....]で、成分毎に取り出せるようにしておく。
	public Deck() {
		cards = new ArrayList<>();
		for(Mark mark : Mark.values() ) {
			for(int i = 1 ; i <= 13 ; i++) {
				this.cards.add(new Card(mark,i));
			}
		}
	}
	//デッキをシャフル
	public List<Card> deckShuffle(){
		Collections.shuffle(cards);
		return cards;
	}

	public Card deal() {
		Card card = cards.get(0);
		cards.remove(0);
		return card;
	}

	public int size() {
		return cards.size();
	}

}

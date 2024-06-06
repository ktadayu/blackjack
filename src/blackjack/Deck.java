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

	//デッキをシャフル
	public List<Card> shuffle() {
		Collections.shuffle(cards);
		System.out.println(cards);
		return cards;
	}

	//スプリット機能テスト用デッキシャッフル
	public List<Card> deckToTestSplit() {
		List<Card> cards = new ArrayList<>();
			for(int i = 1 ; i <=13; i++) {
				for(Mark mark : Mark.values()) {
					cards.add(new Card(mark,i));
				}
			}
			this.cards = cards;
			return this.cards;
	}

	//ブラックジャック機能テスト用デッキシャッフル
	public List<Card> deckToNtBj(){
		List<Card> cards = new ArrayList<>();
		for(int i = 1 ; i <=6; i++) {
			for(Mark mark : Mark.values()) {
				cards.add(new Card(mark,i));
				cards.add(new Card(mark,14-i));
			}
		}
		for(Mark mark : Mark.values()) {
			cards.add(new Card(mark,7));
		}
		this.cards = cards;
		return this.cards;
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

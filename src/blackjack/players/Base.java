package blackjack.players;

import blackjack.cal.Card;
import blackjack.cal.Deck;
import blackjack.cal.Hand;

public abstract class Base {

	protected Hand hand ; //手札
	protected int tips; //チップ数
	protected boolean burst; //バーストしているかの判定 1＝バースト

	//コンストラクタ 手札の初期化
	public Base() {
		hand = new Hand(); //new Cards cards;
	}

	public Hand getHand() {
		return this.hand;
	}

	//手札にカードを追加する
	public void addCard(Card card) {
		hand.getCards().add(card);
	}

	public void removeHand() {
		hand.getCards().clear();
	}

	//カードを引くことについての操作
	public abstract Hand drawCard(Deck deck) ;



}

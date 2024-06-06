import java.util.List;

import blackjack.Card;
import blackjack.Deck;
import blackjack.players.Dealer;
import blackjack.players.Player;

public class Main {

	public static void main(String[] args) {

//		List<Card> cards = new ArrayList<>();
//		for(Mark mark : Mark.values()) {
//			cards.add(new Card(mark,1));
//			System.out.println(mark);
//		}
//		System.out.println(Mark.values());
//		System.out.println(cards);
//
//		Card _card = new Card(Mark.DIA,1);
//		System.out.println(_card);

		Deck deck = new Deck();
		Player player = new Player();//プレイヤー生成
		Dealer dealer = new Dealer();//ディーラー生成

		//山札シャッフル
		List<Card> cards = deck.deckToNtBj();

		System.out.println(cards);

//		//初期手札配布
//		drawCard(player, dealer, deck);
//
//		Hand pHand = player.getHand();
//
//		System.out.println(player.getHand().getCards());
//		System.out.println(pHand.isSplitable());


	}

	public static void drawCard(Player player, Dealer dealer, Deck deck) {
		player.addCard(deck.deal());
		player.addCard(deck.deal());
		dealer.addCard(deck.deal());
		dealer.addCard(deck.deal());
	}

}

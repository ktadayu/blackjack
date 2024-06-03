import blackjack.Deck;
import blackjack.Hand;
import blackjack.players.Dealer;
import blackjack.players.Player;

public class Main {

	public static void main(String[] args) {

		Deck deck = new Deck();
		Player player = new Player();//プレイヤー生成
		Dealer dealer = new Dealer();//ディーラー生成

		//山札シャッフル
		deck.deckShuffle();

		//初期手札配布
		drawCard(player, dealer, deck);

		Hand pHand = player.getHand();

		System.out.println(player.getHand().getCards());
		System.out.println(pHand.isSplitable());


	}

	public static void drawCard(Player player, Dealer dealer, Deck deck) {
		player.addCard(deck.deal());
		player.addCard(deck.deal());
		dealer.addCard(deck.deal());
		dealer.addCard(deck.deal());
	}

}

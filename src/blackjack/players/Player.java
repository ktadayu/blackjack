package blackjack.players;

import blackjack.cal.Deck;
import blackjack.cal.Hand;
import blackjack.cal.PlayerStats;

public class Player extends Base{

	PlayerStats stats;

	public Player() {
		super();
	}

	public Player(int tips) {
		super();
		this.tips = tips;
	}

	public Hand drawCard(Deck deck) {

//		 while(hand.totalValue() < 21) {
//
//		System.out.println("現在の合計:" + hand.totalValue());
//		System.out.println("hit:0 / stand:1");
//		 Scanner scanner = new Scanner(System.in);
//		 int opt = scanner.nextInt();
//			 if(opt == 0) {
//				 addCard(deck.deal());
//				 drawCard(deck);
//			 }
//		return hand;
//		 }

		addCard(deck.deal());

	return hand;
	}

}

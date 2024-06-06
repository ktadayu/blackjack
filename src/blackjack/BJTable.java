package blackjack;

import javax.servlet.http.HttpSession;

import blackjack.players.Dealer;
import blackjack.players.Player;
import model.FlagOwner;
import model.User;

public class BJTable {

	private Deck deck;
	private Player player;
	private Dealer dealer;
	Integer betPoint;
	User user;

	public static String msg;

	//コンストラクタ
	public BJTable(HttpSession session) {
		user = (User) session.getAttribute("USER");
		betPoint = (int) session.getAttribute("BETPOINT");
		user.setNumberOfTips(user.getNumberOfTips() - betPoint);
	}

	public HttpSession startGame(Boolean firstOrNot,HttpSession session) {

		/*
		 * @parm firstOrNot 初回プレイはtrue、リプレイはfalseとして扱う
		 */
		user = (User) session.getAttribute("USER"); //notNull
		deck = (Deck) session.getAttribute("DECK"); //Nullの可能性あり
		player = (Player) session.getAttribute("PLAYER");
		dealer = (Dealer) session.getAttribute("DEALER");
		betPoint = (Integer) session.getAttribute("BETPOINT");

		if(firstOrNot) {
			deck = new Deck(); //デッキ生成
			player = new Player();//プレイヤー生成
			dealer = new Dealer();//ディーラー生成
		}

		//フラグのリセット
		FlagOwner.resetFlag();

		//山札シャッフル
		//deck.deckShuffle();
//		if(firstOrNot) {
//		deck.deckToTestSplit();
//		}
		if(firstOrNot) {
		deck.deckToNtBj();
		}

		//デッキが20枚以下ならデッキの初期化
		deck = deckInit(deck);

		//手札を初期化
		player.removeHand();
		dealer.removeHand();
		drawCard(player, dealer, deck);

		//ナチュラルBJ判定
		if (checkBlackJack(player.getHand())) {
			user.setNumberOfTips((int) (user.getNumberOfTips() + (2.5) * betPoint));
		}
		session.setAttribute("BLACKJACK", checkBlackJack(player.getHand()));

		//splitが可能かどうか
		if(player.getHand().isSplitable()) {
			FlagOwner.validateSplittableFlag();
		}

		//リセット
		session.removeAttribute("plyr1");
		session.removeAttribute("pHand1");
		session.removeAttribute("plyr2");
		session.removeAttribute("pHand2");

		//再登録(sessionには参照が保存されているのでもしかしたら必要がないかもしれない)
		session.setAttribute("USER", user);
		session.setAttribute("DECK", deck);
		session.setAttribute("PLAYER", player);
		session.setAttribute("DEALER", dealer);
		//session.setAttribute("BETPOINT", betPoint);

		return session;
	}


	//手札配布
	public void drawCard(Player player, Dealer dealer, Deck deck) {
		player.addCard(deck.deal());
		player.addCard(deck.deal());
		dealer.addCard(deck.deal());
		dealer.addCard(deck.deal());
	}

	//手札を受け取りTotalValueが21かどうか判定する
	//手札配布時に呼び出すこと(なので上のdrawCardとセットにするべきかもしれない)
	public static boolean checkBlackJack(Hand playerHand) {
		int firstCardNum = playerHand.getCards().get(0).getCardNumber();
		int secondCardNum = playerHand.getCards().get(1).getCardNumber();

		int a = Math.min(firstCardNum, secondCardNum);
		if (playerHand.totalValue() == 21 && a == 1) {
			return true;
		}
		return false;
	}


	//デッキの初期化
	public Deck deckInit(Deck deck) {
		if (deck.size() < 20) {
			deck = new Deck();
			deck.shuffle();
			return deck;
		}
		return deck;
	}

	//勝者を決定する
	// return 1 : プレイヤー勝利
	// return 0 : 引き分け
	// return -1 ： ディーラー勝利
	public static int detWinner(Hand playerHand, Hand dealerHand) {
		if (!playerHand.isBust() && playerHand.totalValue() > dealerHand.totalValue() || dealerHand.totalValue() > 21) {
			msg = "プレイヤーの勝利！";
			return 1;
		}

		if (!playerHand.isBust() && playerHand.totalValue() == dealerHand.totalValue()) {
			msg = "引き分け";
			return 0;
		}

		msg = "ディーラーの勝利！";
		return -1;
	}

}
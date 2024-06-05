package blackjack;

import javax.servlet.http.HttpSession;

import blackjack.players.Dealer;
import blackjack.players.Player;
import model.FlagOwner;
import model.User;

public class BJLogic {

	private Deck deck;
	private Player player;
	private Dealer dealer;
	Integer betPoint;
	User user;

	public static String msg;

	//コンストラクタ
	public BJLogic(HttpSession session) {
		user = (User) session.getAttribute("USER");
		betPoint = (int) session.getAttribute("BETPOINT");
		user.setNumberOfTips(user.getNumberOfTips() - betPoint);
	}

	//ゲームの初期化
	public HttpSession initializeBJ(HttpSession session) {

		deck = new Deck(); //デッキ生成
		player = new Player();//プレイヤー生成
		dealer = new Dealer();//ディーラー生成

		//フラグのリセット
		FlagOwner.resetFlag();

		//山札シャッフル
		//deck.deckShuffle();
		deck.deckToTestSplit();

		//初期手札配布
		drawCard(player, dealer, deck);


		//ナチュラルBJ判定
		if (checkBlackJack(player.getHand())) {
			session.setAttribute("BLACKJACK", true);
			user.setNumberOfTips((int) (user.getNumberOfTips() + (2.5) * betPoint));
		}

		//splitが可能かどうか
		if(player.getHand().isSplitable()) {
			FlagOwner.validateSplittableFlag();;
		}


		session.removeAttribute("plyr1");
		session.removeAttribute("pHand1");
		session.removeAttribute("plyr2");
		session.removeAttribute("pHand2");

		session.setAttribute("DECK", deck);
		session.setAttribute("PLAYER", player);
		session.setAttribute("DEALER", dealer);
		session.setAttribute("USER", user); //bet額を支払ったuser,BJで稼いだuser:更新

		return session;
	}

	//再プレイ
	public HttpSession ReplayBJ(HttpSession session) {

		Deck deck = (Deck) session.getAttribute("DECK");
		Player player = (Player) session.getAttribute("PLAYER");
		Dealer dealer = (Dealer) session.getAttribute("DEALER");
		Integer betPoint = (Integer) session.getAttribute("BETPOINT");
		User user = (User) session.getAttribute("USER");

		//フラグのリセット
		FlagOwner.resetFlag();

		//デッキが20枚以下ならデッキの初期化
		deck = deckInit(deck);

		//手札を初期化
		player.removeHand();
		dealer.removeHand();
		drawCard(player, dealer, deck);

		//ナチュラルBJ判定
		if (checkBlackJack(player.getHand())) {
			session.setAttribute("BLACKJACK", true);
			user.setNumberOfTips((int) (user.getNumberOfTips() + (2.5) * betPoint));
		}

		//splitが可能かどうか
		if(player.getHand().isSplitable()) {
			FlagOwner.validateSplittableFlag();
		}

		//リセット
		session.removeAttribute("plyr1");
		session.removeAttribute("pHand1");
		session.removeAttribute("plyr2");
		session.removeAttribute("pHand2");

		//再登録
		session.setAttribute("USER", user);
		session.setAttribute("DECK", deck);
		session.setAttribute("PLAYER", player);
		session.setAttribute("DEALER", dealer);
		session.setAttribute("BETPOINT", betPoint);

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
	public boolean checkBlackJack(Hand playerHand) {
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
			deck.deckShuffle();
			return deck;
		}
		return deck;
	}

	//勝者を決定する
	// return 1 : プレイヤー勝利
	// return 0 : 引き分け
	// return -1 ： ディーラー勝利
	public static int detWinner(Hand playerHand, Hand dealerHand) {
		if (playerHand.totalValue() > dealerHand.totalValue() || dealerHand.totalValue() > 21) {
			msg = "プレイヤーの勝利！";
			return 1;
		}

		if (playerHand.totalValue() == dealerHand.totalValue()) {
			msg = "引き分け";
			return 0;
		}

		msg = "ディーラーの勝利！";
		return -1;
	}

}
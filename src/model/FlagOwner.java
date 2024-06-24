package model;

//複数クラス・通信に跨って管理が必要な状態をフラグで管理する。
public class FlagOwner {

	static Boolean endGame = false; //ゲーム終了
	static Boolean endUsualGame = false; //通常のゲームの終了
	static Boolean endPlayer1 = false; //プレイヤー1の終了
	static Boolean endPlayer2 = false; //プレイヤー2の終了
	static Boolean splittable = false; //split可能ならtrue
	static Boolean splittingFlag = false; //split中のフラグ
	static Boolean blackjack = false ;
//チェックメソッド
	public static Boolean checkEndGame() {
		endGame = (endUsualGame && !splittingFlag) || (endPlayer1 && endPlayer2);
		return endGame ;
	}
	public static Boolean checkUsualGameEnd() {
		return endUsualGame ;
	}
	public static Boolean checkPlayer1End() {
		return endPlayer1;
	}
	public static Boolean checkPlayer2End() {
		return endPlayer2;
	}
	public static Boolean checkSplitting() {
		return splittingFlag;
	}
	public static Boolean checkSplittable() {
		return splittable;
	}
	public static Boolean checkBlackJack() {
		return blackjack;
	}


//true化
	public static void validateGameEnd() {
		endGame = true;
	}
	public static void validatePlayer1() {
		endPlayer1 = true;
	}
	public static void validatePlayer2() {
		endPlayer2 = true;
	}
	public static void validateUsualGameEnd() {
		endUsualGame = true;
	}
	public static void validateSplittableFlag() {
		splittable  = true;
	}
	public static void validateSplit() {
		splittingFlag = true;
	}
	public static void validateBlackJack() {
		blackjack = true;
	}


	//false化
	public static void inValidateSplittableFlag() {
		splittable = false;
	}
//リセット
	public static void resetFlag() {
		 endGame = false;
		 endPlayer1 = false;
		 endPlayer2 = false;
		 endUsualGame = false;
		 splittable = false;
		 splittingFlag = false;
		 blackjack = false;
	}

}
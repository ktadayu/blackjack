package blackjack;

public class Card {

	public enum Mark{
			CLUB("club",1),
			DIA("diamond",2),
			HEART("heart",3),
			SPADE("spade",4);

			private String name;
			private int markNum;

			private Mark(String jname, int markNum) {
				this.name = jname;
				this.markNum =markNum ;
			}

			public String getJname() {
				return this.name;
			}

			public int getMarkNum() {
				return this.markNum;
			}
	}

	//1,2,...,10,11,12,13
	private int cardNumber;
	private Mark mark;

	//コンストラクタ
	public Card(Mark mark, int cardNumber) {
		this.mark = mark;
		this.cardNumber = cardNumber;
	}

	public int getCardNumber() {
		return this.cardNumber;
	}

	//カードの数字から値(1~10)を返却
	public int getValue() {
		switch(this.cardNumber) {
		case 11:
		case 12:
		case 13:
			return 10;
		case 1:
			return 1;
		default:
			return this.cardNumber;
		}
	}

	//カードの値→カードの数字の表示必要？？？？？
	public String toString() {
		String[] numbers = {"01","02","03","04","05","06","07","08","09","10","11","12","13"};
//		String cardNumber = String.format("%02c", numbers[this.cardNumber-1] );
		return   this.mark.getJname()+ "_" + numbers[this.cardNumber-1] ;
	}

}

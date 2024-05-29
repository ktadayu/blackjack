package blackjack.cal;

public class Card {

	public enum Mark{
			CLUB("クラブ",1),
			DIA("ダイヤ",2),
			HEART("ハート",3),
			SPADE("スペード",4);

			private String jname;
			private int markNum;

			private Mark(String jname, int markNum) {
				this.jname = jname;
				this.markNum =markNum ;
			}

			public String getJname() {
				return this.jname;
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
		String[] numbers = {"1","2","3","4","5","6","7","8","9","10","J","Q","K"};
		return "[" + this.mark.getJname()+ " , " + numbers[this.cardNumber-1] + "]";
	}

}

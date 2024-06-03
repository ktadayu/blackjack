package model;

public class  User{
	private int user_id;
	private String user_name;
	private String user_password;
	private String user_nickname;
	private int number_of_tips;
	private float rate;

	//コンストラクタ
	//new User(id, name, password, user_nickname, number_of_tips);
	public User(int user_id, String user_name, String user_password, String user_nickname,int number_of_tips) {
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_nickname = user_nickname;
		this.number_of_tips = number_of_tips;
		}

	public User(String user_name, String user_password, String user_nickname) {
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_nickname = user_nickname;
		}

	public User(String user_nickname, String number_of_tips) {
		this.user_nickname = user_nickname;
		this.number_of_tips = Integer.parseInt(number_of_tips);
		}
	//rate
	public User(String user_nickname, int number_of_tips, float rate) {
		this.user_nickname = user_nickname;
		this.number_of_tips = number_of_tips;
		this.rate = rate;
	}

	//勝率取得
	public String getRate() {
		String strate = String.format("%.2f", this.rate * 100.0 );
		return strate;
	}


	/*
	 * ユーザーネーム
	 */
	public String getUserName() {
			return user_name;
	}

	/*
	 * ニックネーム
	 */
	public String getUserNickname() {
			return user_nickname;
	}

	public void setUserNickname(String user_nickname) {
			this.user_nickname = user_nickname;
	}

	/*
	 * パスワード
	 */
	public String getUserPassword() {
			return user_password;
		}

	/*
	 * 現在のチップ枚数
	 */
	public int getNumberOfTips() {
		return this.number_of_tips;
	}

	public void setNumberOfTips(int tips) {
		this.number_of_tips = tips;
	}

	/*
	 * id
	 */
	public int getUserId() {
		return this.user_id;
	}


}


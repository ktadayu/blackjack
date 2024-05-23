package model;

public class  User{
	private String user_name;
	private String user_password;
	private String user_nickname;
	private int number_of_tips;

	//id,name,pass全部貰う
	public User(String user_name, String user_password, String user_nickname) {
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_nickname = user_nickname;
		}

	/*
	 * ユーザーネーム
	 */

	public String getUserName() {
			return user_name;
		}

	public void setUserName(String user_name) {
			this.user_name = user_name;
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

	public void setUserPassword(String user_password) {
			this.user_password = user_password;
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


	}


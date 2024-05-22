package model;

public class  User{
	private String user_name;
	private String user_password;
	private String user_nickname;

	//id,name,pass全部貰う
	public User(String user_name, String user_password, String user_nickname) {
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_nickname = user_nickname;
		}

	public String getUserName() {
			return user_name;
		}

	public void setUserName(String user_name) {
			this.user_name = user_name;
		}

	public String getUserNickname() {
			return user_nickname;
		}


	public void setUserNickname(String user_nickname) {
			this.user_nickname = user_nickname;
		}


	public String getUserPassword() {
			return user_password;
		}

	public void setLoginPassword(String user_password) {
			this.user_password = user_password;
		}
	}


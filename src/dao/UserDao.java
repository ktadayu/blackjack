package dao;

import java.sql.SQLException;

import exception.MyException;
import model.User;

public class UserDao extends BaseDao{

/*
 * データベース接続処理 getConnection
 */
	public UserDao() throws MyException {
		super();
	}

/*
 * ログイン処理
 */
	public User doLogin(String user_name, String user_password)
		throws MyException {

			User loginUser = null;
			try {
				String sql = "SELECT * FROM users WHERE user_name = ? AND user_password = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, user_name);
				ps.setString(2, user_password);
				rs = ps.executeQuery();
				while(rs.next()) {
					String name = rs.getString("user_name");
					String password = rs.getString("user_password");
					String user_nickname = rs.getString("user_nickname");
					loginUser = new User(name, password, user_nickname);
				}

				// ログイン結果を確認
				if(loginUser == null) {
					System.out.println("ログイン処理失敗");
					throw new MyException("ユーザー名・パスワードのいずれかに誤りがあります。");
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new MyException("SQL実行中に例外が発生しました");
			}finally {
				close();
			}

			System.out.println("ログイン完了");
		return loginUser;
	}

/*
 * 新規登録処理
 */
	public void doCreate(String user_name, String user_password, String user_nickname) throws MyException{
		try {
		String sql = "INSERT INTO `users` (`user_id`, `user_name`, `user_password`, `user_nickname`, `number_of_tips`) VALUES (NULL, ?, ?, ?, '100');";
		ps = con.prepareStatement(sql);
		ps.setString(1, user_name);
		ps.setString(2, user_password);
		ps.setString(3,user_nickname);
		ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new MyException("作成できませんでした");
		}finally {
			close();
		}

	}

/*
 * 検索
 */
	public boolean doSearch(int id, String name, String pass) {
		boolean value = true;
		return value;
	}


/*
 * 削除処理
 */

	public void doDelete(String user_name) throws  MyException{

		try {
			String sql = "DELETE FROM users WHERE `users`.`user_name` = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, user_name);
			ps.executeUpdate();
			System.out.println("削除完了");

		}catch(SQLException e) {
			e.printStackTrace();
			throw new MyException("SQLエラー");
		}finally {
			close();
		}


	}


}

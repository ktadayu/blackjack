package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.MyException;
import model.User;

public class UserDao extends BaseDao {

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
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString("user_id"));
				String name = rs.getString("user_name");
				String password = rs.getString("user_password");
				String user_nickname = rs.getString("user_nickname");
				int number_of_tips = Integer.parseInt(rs.getString("number_of_tips"));
				loginUser = new User(id, name, password, user_nickname, number_of_tips);
			}
			// ログイン結果を確認
			if (loginUser == null) {
				throw new MyException("ユーザー名・パスワードのいずれかに誤りがあります。");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("SQL実行中に例外が発生しました");
		} finally {
			close();
		}

		System.out.println("ログイン完了");
		return loginUser;
	}

	/*
	 * 新規登録処理
	 */
	public void doCreate(String user_name, String user_password, String user_nickname) throws MyException {
		try {
			String sql = "INSERT INTO `users` (`user_id`, `user_name`, `user_password`, `user_nickname`, `number_of_tips`) VALUES (NULL, ?, ?, ?, '100');";
			ps = con.prepareStatement(sql);
			ps.setString(1, user_name);
			ps.setString(2, user_password);
			ps.setString(3, user_nickname);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("作成できませんでした");
		} finally {
			close();
		}
	}

	/*
	 * チップ数更新処理
	 */
	public void updateNumberOfTips(User user) throws MyException {
		try {
			String sql = "UPDATE `users` SET `number_of_tips` = ? WHERE `users`.`user_id` = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, user.getNumberOfTips());
			ps.setInt(2, user.getUserId());
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("履歴の登録ができませんでした");
		} finally {
			close();
		}
	}

	/*
	 * 戦歴の登録
	 */

	/*
	 * 戦績取得
	 */

	/*
	 * 検索して全件取得
	 */
	//全ユーザーリスト取得
	public List<User> selectAllUsers() throws MyException {

		List<User> userList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM users";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String user_nickname = rs.getString("user_nickname");
				String number_of_tips = rs.getString("number_of_tips");
				User user = new User(user_nickname, number_of_tips);
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("リスト取得について予期せぬ失敗");
		}

		return userList;
	}

	/*
	 * ランキングTOP5取得
	 */
	//チップ数トップ5
	public List<User> selectTopUsers() throws MyException {

		List<User> userList = new ArrayList<>();
		try {
			String sql = "SELECT * FROM users ORDER BY `users`.`number_of_tips` DESC LIMIT 5";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String user_nickname = rs.getString("user_nickname");
				String number_of_tips = rs.getString("number_of_tips");
				//勝率を追加すること
				User user = new User(user_nickname, number_of_tips);
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("リスト取得について予期せぬ失敗");
		}
		return userList;
	}

	//勝率TOP5

	/*
	 * 削除処理
	 */
	public void doDelete(String user_name) throws MyException {

		try {
			String sql = "DELETE FROM users WHERE `users`.`user_name` = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, user_name);
			ps.executeUpdate();
			System.out.println("削除完了");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("SQLエラー");
		} finally {
			close();
		}
	}

	public void doDeleteByNickname(String user_nickname) throws MyException {

		try {
			String sql = "DELETE FROM users WHERE `users`.`user_nickname` = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, user_nickname);
			ps.executeUpdate();
			System.out.println("削除完了");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("SQLエラー");
		} finally {
			close();
		}
	}

}

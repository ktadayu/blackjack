package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exception.MyException;
import model.History;
import model.User;

public class HistoryDao extends BaseDao {
	/*
	 * データベース接続処理 getConnection
	 */
	public HistoryDao() throws MyException {
		super();
	}

	/*
	 * 戦績の登録
	 */
	public void addToHistory(User user, int amount_of_changes) throws MyException {

		try {
			String sql = "INSERT INTO `score_history` (`history_id`, `user_id`, `amount_of_changes`, `timestamp`, `datetime`) VALUES (NULL, ?, ?, current_timestamp(), current_timestamp())";
			ps = con.prepareStatement(sql);
			ps.setInt(1, user.getUserId());
			ps.setInt(2, amount_of_changes);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("履歴の登録ができませんでした");
		} finally {
			close();
		}
	}

	/*
	 * 全戦績の取得
	 */
	public List<History> selectAllHistory(User user) throws MyException {

		List<History> historyList = new ArrayList<>();

		try {
			String sql = "SELECT * FROM score_history where `user_id` = ?";
			ps = con.prepareStatement(sql);
			ps.setInt(1, user.getUserId());
			rs = ps.executeQuery();
			while (rs.next()) {
				int historyId = rs.getInt("history_id");
				int amountOfChanges = rs.getInt("amount_of_changes");
				String time = rs.getString("timestamp");
				History history = new History(historyId, amountOfChanges, time);
				historyList.add(history);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("リスト取得について予期せぬ失敗");
		}

		return historyList;
	}

	//勝率を計算し勝率の降順で5つ取得する
	public List<User> selectTopRateUsers() throws MyException {

		List<User> userList = new ArrayList<>();
		try {
			String sql = "select users.user_nickname,\r\n" +
					"		score_history.user_id,\r\n" +
					"        users.number_of_tips,\r\n" +
					"		sum(case when amount_of_changes > 0 then 1 else 0 end) as wins,\r\n" +
					"        count(*),\r\n" +
					"        sum(case when amount_of_changes > 0 then 1 else 0 end)/count(*) as rate\r\n" +
					"from score_history\r\n" +
					"inner join users\r\n" +
					"on users.user_id = score_history.user_id\r\n" +
					"group by score_history.user_id\r\n" +
					"order by rate desc limit 5;";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String user_nickname = rs.getString("user_nickname");
				int tips = rs.getInt("number_of_tips");
				float rate = rs.getFloat("rate");
				User user = new User(user_nickname, tips, rate);
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("リスト取得について予期せぬ失敗");
		}

		return userList;
	}

}

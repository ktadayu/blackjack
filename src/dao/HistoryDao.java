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
	 * 戦績最新100件
	 */
	public List<History> selectHistory(User user) throws MyException {

		List<History> historyList = new ArrayList<>();

		try {
			String sql = "select * from score_history where `user_id` = ? order by datetime desc limit 100";
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

	//全ユーザーの勝率を計算し勝率の降順で取得
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
					"order by rate desc;";

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

	//特定のユーザーの勝率を取得

	public User selectRate(User user) throws MyException {

		User userStats = null;

		try {
			String sql = "select users.user_nickname, score_history.user_id, users.number_of_tips, sum(case when amount_of_changes > 0 then 1 else 0 end) as wins, count(*), sum(case when amount_of_changes > 0 then 1 else 0 end)/count(*) as rate from score_history inner join users on users.user_id = score_history.user_id where score_history.user_id = ?;";

			ps = con.prepareStatement(sql);
			ps.setInt(1, user.getUserId());
			rs = ps.executeQuery();
			while (rs.next()) {
				String user_nickname = rs.getString("user_nickname");
				int tips = rs.getInt("number_of_tips");
				int wins = rs.getInt("wins");
				int numOfPlays = rs.getInt("count(*)");
				float rate = rs.getFloat("rate");

				userStats = new User(user_nickname, tips, wins ,numOfPlays , rate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new MyException("リスト取得について予期せぬ失敗");
		}

		return userStats;
	}

}

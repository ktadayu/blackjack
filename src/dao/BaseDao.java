package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exception.MyException;


public abstract class BaseDao {

	protected Connection con = null;
	protected PreparedStatement ps = null;
	protected ResultSet rs = null;

	//コンストラクタ
	public BaseDao() throws MyException {
		getConnection();
	}

	private void getConnection() throws MyException {
		try {
			if (con == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");

				String url  = "jdbc:mysql://localhost/blackjack";
				String user = "root";
				String password = "";
				// DB接続
				con = DriverManager.getConnection(url, user, password);
				System.out.println("DB接続完了");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new MyException("JDBCドライバが見つかりません");
		} catch (SQLException e) {
			e.printStackTrace();
			close();
			throw new MyException("SQL実行中に例外が発生しました");
		}
	}

	//接続全部切る
	protected void close() throws MyException {
		try {
			if (con != null) {
				con.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
			System.out.println("con,ps,rs:closed");
		} catch (SQLException e) {
			throw new MyException("close処理中に例外が発生しました");
		}
	}
}
package com.salesrevolution.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.QueryOptions;
import com.datastax.driver.core.Session;

public class DBConnection {
	/**
	 * cassandraのsessionを返すメソッド
	 * リファレンス => http://blog.katty.in/6408
	 * @return session
	 */
	public static Session connect() {
		Cluster cluster = Cluster
				.builder()
				.addContactPoint("36.55.245.134")
				.withPort(9042)
				.withQueryOptions(
						new QueryOptions()
								.setConsistencyLevel(ConsistencyLevel.ONE))
				.build();
		Session session = cluster.connect();	
		return session;
	}
	
	/**
	 * mysqlのコネクションを取得
	 * @return mysql conncetion
	 * @throws SQLException
	 */
	public static java.sql.Connection mysqlConnect() throws SQLException {
		Connection con = null;
		String url = "jdbc:mysql://36.55.245.134:環境設定資料のポート番号/tmp";
		String user = "VPSのログイン名";
		String password = "VPSのパスワード";

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			con = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}

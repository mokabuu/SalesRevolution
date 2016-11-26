package com.salesrevolution.util;

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
}

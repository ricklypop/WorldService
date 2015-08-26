package com.rd.lbs.services;

//STEP 1. Import required packages
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.rd.lbs.dao.LeaderBoard;

public class DataServices {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/test";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "Welcome1";
	static Connection conn = null;
	static Statement stmt = null;
	static int PAGE_SIZE = 10;

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException | ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	public DataServices() {

	}
	
	public synchronized static void putData(String userid, String score){
		try {
			stmt = conn.createStatement();
			String sql = "update leaderboard set score =" + score + " where userid =" + userid;
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized static void postData(String string, String username, int score){
		try {
			stmt = conn.createStatement();
			String sql = "insert into leaderboard (userid,username,score) values(\"" + string + "\",\"" + username + "\","
			+ score + ")";
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized static LeaderBoard[] getLeaderboardDesc() {

		LeaderBoard[] lb = new LeaderBoard[10];
		try {

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql = "select * from leaderboard order by score desc";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			int i = 0;
			while (rs.next()) {
				LeaderBoard leaderBoard = new LeaderBoard();
				// Retrieve by column name
				leaderBoard.setEntrytime(rs.getString("entrytime"));
				leaderBoard.setUserid(rs.getString("userid"));
				leaderBoard.setUsername(rs.getString("username"));
				leaderBoard.setScore(rs.getInt("score"));
				
				lb[i] = leaderBoard;
				i++;
				//System.out.println(leaderBoard.toString());
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			

			return lb;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}
		} 
		return null;
	}
	
	public synchronized static List<LeaderBoard> getPage(String page) {
	//	List<LeaderBoard> lb = new LeaderBoard[PAGE_SIZE];
		List<LeaderBoard> lb = new ArrayList<LeaderBoard>();
//		for(int z = 0; z < PAGE_SIZE; z++){
//			LeaderBoard leaderBoard = new LeaderBoard();
//			leaderBoard.setEntrytime("");
//			leaderBoard.setUserid("");
//			leaderBoard.setUsername("");
//			leaderBoard.setScore(0);
//			lb[z] = leaderBoard;
//		}
		
		try {

			int x = (Integer.parseInt(page)-1) * PAGE_SIZE;
			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "select * from leaderboard order by score desc limit " + x + "," + PAGE_SIZE;
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			//int i = 0;
			while (rs.next()) {
				LeaderBoard leaderBoard = new LeaderBoard();
				// Retrieve by column name
				leaderBoard.setEntrytime(rs.getTimestamp("entrytime").toString());
				leaderBoard.setUserid(rs.getString("userid"));
				leaderBoard.setUsername(rs.getString("username"));
				leaderBoard.setScore(rs.getInt("score"));
				
				lb.add(leaderBoard);
				//i++;
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			return lb;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			
		} // end try
		return null;
	}

	public synchronized static String getTotal() {
	try {

	
		System.out.println("Creating statement...");
		stmt = conn.createStatement();
		String sql;
		sql = "select count(*) from leaderboard";
		ResultSet rs = stmt.executeQuery(sql);

		// STEP 5: Extract data from result set
	String count = null;
		while (rs.next()) {
			count = rs.getString(1);
			//System.out.println(leaderBoard.toString());
		}
		// STEP 6: Clean-up environment
		rs.close();
		stmt.close();
		

		return count;
	} catch (SQLException se) {
		// Handle errors for JDBC
		se.printStackTrace();
	} catch (Exception e) {
		// Handle errors for Class.forName
		e.printStackTrace();
	} finally {
		// finally block used to close resources
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException se2) {
		} // nothing we can do
		
	} // end try
	return null;
	
	}

	public static synchronized String getHighscore(String userID) {
		try {
			// STEP 4: Execute a query
			stmt = conn.createStatement();
			String sql;
			sql = "select score from leaderboard where userID =\"" + userID + "\"";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			int i = 0;
			while (rs.next()) {
				i = rs.getInt("score");
				System.out.println(i);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			

			return "" + i;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			
		} // end try
		return "";
	}
	
	public static synchronized String getUsername(String userID) {
		try {
			// STEP 4: Execute a query
			stmt = conn.createStatement();
			String sql;
			sql = "select username from leaderboard where userID =\"" + userID + "\"";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			String user = "";
			while (rs.next()) {
				user = rs.getString("username");
				System.out.println(user);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			return user;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			
		} // end try
		return null;
	}
}


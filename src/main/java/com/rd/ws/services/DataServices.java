package com.rd.ws.services;

//STEP 1. Import required packages
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataServices {
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/main";

	// Database credentials
	static final String USER = "root";
	static final String PASS = "jenn1fer123!";
	static Connection conn = null;
	static Statement stmt = null;

	static {
		try {
			Class.forName(JDBC_DRIVER );

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");

			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (SQLException | ClassNotFoundException e) {

			e.printStackTrace();
		}
	}

	public DataServices() {

	}

	public synchronized static void putID(String id){
		try {
			stmt = conn.createStatement();
			PreparedStatement sql = conn.prepareStatement("insert into worldtable(ID) values(\"" +id + "\")");
			System.out.println(sql);
			sql.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public synchronized static void putWorld(String id, String world){
		try {
			stmt = conn.createStatement();
				PreparedStatement sql = conn.prepareStatement("update worldtable set world = ? where ID = ?");
			System.out.println(sql);

			sql.setString(1, world);
			sql.setString(2, id);
			sql.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public synchronized static void putLastWorld(String id, String lastWorld){
		try {
			stmt = conn.createStatement();
			PreparedStatement sql = conn.prepareStatement("update worldtable set lastWorld = ? where ID = ?");
			System.out.println(sql);
			sql.setString(1, lastWorld);
			sql.setString(2, id);
			sql.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public synchronized static void putWorldName(String id, String worldName){
		try {
			stmt = conn.createStatement();
			PreparedStatement sql = conn.prepareStatement("update worldtable set worldName = ? where ID = ?");
			System.out.println(sql);
			sql.setString(1, worldName);
			sql.setString(2, id);
			sql.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static synchronized String checkID(String id) {
		try {
			stmt = conn.createStatement();
			String sql;
			sql = "select * from worldtable where id =\"" + id + "\"";
			ResultSet rs = stmt.executeQuery(sql);

			String s = "false";
			while (rs.next()) {
				s = "true";
			}
			rs.close();
			stmt.close();


			return s;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}

		}
		return "";
	}

	public static synchronized String getWorld(String id) {
		try {
			stmt = conn.createStatement();
			String sql;
			sql = "select world from worldtable where id =\"" + id + "\"";
			ResultSet rs = stmt.executeQuery(sql);

			String s = "";
			while (rs.next()) {
				s = rs.getString("world");
				System.out.println("World:" + s);
			}
			rs.close();
			stmt.close();


			return s;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}

		}
		return "";
	}

	public static synchronized String getLastWorld(String id) {
		try {
			stmt = conn.createStatement();
			String sql;
			sql = "select lastWorld from worldtable where id =\"" + id + "\"";
			ResultSet rs = stmt.executeQuery(sql);

			String s = "";
			while (rs.next()) {
				s = rs.getString("lastWorld");
				System.out.println("Last World: " + s);
			}
			rs.close();
			stmt.close();


			return s;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}

		}
		return "";
	}

	public static synchronized String getWorldName(String id) {
		try {
			stmt = conn.createStatement();
			String sql;
			sql = "select worldName from worldtable where id =\"" + id + "\"";
			ResultSet rs = stmt.executeQuery(sql);

			String s = "";
			while (rs.next()) {
				s = rs.getString("worldName");
				System.out.println(s);
			}
			rs.close();
			stmt.close();


			return s;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}

		}
		return "";
	}

	public static synchronized HashMap<String, String> getWorldList() {
		try {
			HashMap<String, String> ids = new HashMap<>();
			stmt = conn.createStatement();
			String sql;
			sql = "select id, worldName from worldtable";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String key = rs.getString("id");
				String val = rs.getString("worldName");
				ids.put(key, val);
				System.out.println(key + ":" + val);
			}
			rs.close();
			stmt.close();


			return ids;
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			}

		}
		return new HashMap<>();
	}

	public static synchronized boolean LogIn(String id) {
		String logIn = checkID(id);
		if(logIn.equals("true"))
			return true;
		else
			return false;
	}
}


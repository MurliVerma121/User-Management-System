package com.nit.mv.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.nit.mv.pojo.UserAccount;

public class UserAccountDAO {
	
	private Connection connection;
	
	private PreparedStatement insertPstmt;
	private PreparedStatement loginPstmt;
	private PreparedStatement selectPstmt;
	private PreparedStatement updatePstmt;
	private PreparedStatement deletePstmt;
	
	public boolean openConnection() {

		try {
			Properties props = new Properties();
			
			InputStream inputStream = 
					getClass().getClassLoader().getResourceAsStream("driverinfo.properties");
			
			props.load(inputStream);

			String dbDriver = props.getProperty("DB_DRIVER");
			String dbURL 	= props.getProperty("DB_URL");
			String dbUSN	= props.getProperty("DB_USN");
			String dbPWD 	= props.getProperty("DB_PWD");
			
			Class.forName(dbDriver);
			connection = DriverManager.getConnection(dbURL, dbUSN, dbPWD);
			
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			connection = 
//					DriverManager.getConnection(
//							"jdbc:oracle:thin:@localhost:1521:Orcl",
//							"c##murli11",
//							"verma21"
//							);
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void closeConnection() {
		try {
			connection.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean createPreparedStatements() {
		try {
			StringBuilder insertQueryBuilder = new StringBuilder();
			insertQueryBuilder.append("INSERT INTO user_account(user_id, username, password, email, mobile) ");
			insertQueryBuilder.append("values(?, ?, ?, ?, ?)");
			insertPstmt = connection.prepareStatement(insertQueryBuilder.toString());
			
			StringBuilder loginQueryBuilder = new StringBuilder();
			loginQueryBuilder.append("SELECT user_id FROM user_account ");
			loginQueryBuilder.append("WHERE username=? ");
			loginQueryBuilder.append("AND password=? ");
			loginPstmt = connection.prepareStatement(loginQueryBuilder.toString());
			
			StringBuilder selectQueryBuilder = new StringBuilder();
			selectQueryBuilder.append("SELECT * FROM user_account ");
			selectQueryBuilder.append("WHERE user_id=?");
			selectPstmt = connection.prepareStatement(selectQueryBuilder.toString());
			
			StringBuilder updateQueryBuilder = new StringBuilder();
			updateQueryBuilder.append("UPDATE user_account ");
			updateQueryBuilder.append("SET password=?, email=?, mobile=? ");
			updateQueryBuilder.append("WHERE user_id=?");
			updatePstmt = connection.prepareStatement(updateQueryBuilder.toString());
			
			StringBuilder deleteQueryBuilder = new StringBuilder();
			deleteQueryBuilder.append("DELETE FROM user_account ");
			deleteQueryBuilder.append("WHERE user_id=?");
			deletePstmt = connection.prepareStatement(deleteQueryBuilder.toString());
			
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();	
		}
		
		return false;
	}
	
	public void closePreparedStements() {
		try {insertPstmt.close();}
		catch (SQLException e) {e.printStackTrace();}
		
		try {loginPstmt.close();}
		catch (SQLException e) {e.printStackTrace();}
		
		try {selectPstmt.close();}
		catch (SQLException e) {e.printStackTrace();}
		
		try {updatePstmt.close();}
		catch (SQLException e) {e.printStackTrace();}
		
		try {deletePstmt.close();}
		catch (SQLException e) {e.printStackTrace();}
		
	}
 
	public boolean create(UserAccount account) {
		
		try {
			insertPstmt.setLong(1, account.getUserId());
			insertPstmt.setString(2, account.getUsername());
			insertPstmt.setString(3, account.getPassword());
			insertPstmt.setString(4, account.getEmail());
			insertPstmt.setLong(5, account.getMobile());
			
			insertPstmt.executeUpdate(); 
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public long login(String username, String password) {
		try {
			loginPstmt.setString(1, username);
			loginPstmt.setString(2, password);
			
			ResultSet rs = loginPstmt.executeQuery();
			if(rs.next()) 
				return rs.getLong(1);
				
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public UserAccount getAccount(long userid) {
		
		UserAccount account = new UserAccount();
		
		ResultSet rs = null;
		try {
			selectPstmt.setLong(1, userid);
			rs = selectPstmt.executeQuery();
			
			if(rs.next()) {
				account.setUserId(userid);
				account.setUsername(rs.getString(2));
				account.setPassword(rs.getString(3));
				account.setEmail(rs.getString(4));
				account.setMobile(rs.getLong(5));

				return account;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		
		} finally {
			try {
				if(rs != null)
					rs.close();
			}
			catch(SQLException e) { 
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public ArrayList<UserAccount> getAccounts() {
		
		ArrayList<UserAccount> accountsList = new ArrayList<>();
		
		try ( //Java 7 try-with-resource
			Statement stmt 	= connection.createStatement();
			ResultSet rs 	= stmt.executeQuery("SELECT * FROM user_account order by user_id");
		){
			while(rs.next()) {
				UserAccount account = new UserAccount();

				account.setUserId(rs.getLong(1));
				account.setUsername(rs.getString(2));
				account.setPassword(rs.getString(3));
				account.setEmail(rs.getString(4));
				account.setMobile(rs.getLong(5));

				accountsList.add(account);
			}
			
			return accountsList;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public UserAccount update(long userId) {
		
		return getAccount(userId);
	}

	public boolean save(UserAccount account) {
		try {
			updatePstmt.setString(1, account.getPassword());
			updatePstmt.setString(2, account.getEmail());
			updatePstmt.setLong(3, account.getMobile());
			updatePstmt.setLong(4, account.getUserId());
			
			int updatedCount = updatePstmt.executeUpdate();
			
			if(updatedCount == 0)
				return false;
			else 
				return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(long userId) {
		try {
			deletePstmt.setLong(1, userId);
			int deletedRows = deletePstmt.executeUpdate();
			
			if(deletedRows==1)
				return true;
			else
				return false;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
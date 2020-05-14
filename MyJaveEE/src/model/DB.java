package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import beans.User;

public class DB {
	private final static String USER ="root";
	private final static String PASS_WORD ="root";
	private final static String JDBC ="com.mysql.cj.jdbc.Driver";
	private final static String DB_NAME ="iii";
	private final static String SERVER_TIMEZONE ="?serverTimezone=Asia/Taipei";
	private final static String URL ="jdbc:mysql://localhost:3307/" + DB_NAME + SERVER_TIMEZONE;

	private Connection con;
	
	
	
	public void doConnect() {
		try {
			Class.forName(JDBC);
			con = DriverManager.getConnection(URL, USER, PASS_WORD);
			System.out.println("doConnect():成功");
		}catch (Exception e) {
			System.out.println("doConnect()失敗:" + e.toString());
		}	
	}
	
	public void doClose() {
		try {
			con.close();
			System.out.println("doClose():成功");
		} catch (Exception e) {
			System.out.println("doClose()失敗:" + e.toString());
		}
	}
	
	public void addUser(User user) {
		String sql ="INSERT INTO user(name,account,password,hash,active)VALUES(?,?,?,?,?)";
		doConnect();
		
		try {
		
			
			
			

		}catch (Exception e) {
			System.out.println("addUser()錯誤:" + e.toString());

		}
	}
	
	
	
	
}

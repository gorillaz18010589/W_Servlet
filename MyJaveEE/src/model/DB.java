package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import beans.UserBean;

public class DB {
	private final static String USER ="root";
	private final static String PASS_WORD ="root";
	private final static String JDBC ="com.mysql.cj.jdbc.Driver";
	private final static String DB_NAME ="text";
	private final static String SERVER_TIMEZONE ="?serverTimezone=Asia/Taipei";
	private final static String URL ="jdbc:mysql://localhost:3307/" + DB_NAME + SERVER_TIMEZONE;
	private Connection con;
	
	
	
	public void doConnect() {
		try {
			Class.forName(JDBC);
			con = DriverManager.getConnection(URL, USER, PASS_WORD);
			System.out.println("doConnect():���\");
		}catch (Exception e) {
			System.out.println("doConnect()����:" + e.toString());
		}	
	}
	
	public void doClose() {
		try {
			con.close();
			System.out.println("doClose():���\");
		} catch (Exception e) {
			System.out.println("doClose()����:" + e.toString());
		}
	}
	
	public void addUser(UserBean user) {
		String sql ="INSERT INTO user(name,account,password,hash,active)VALUES(?,?,?,?,?)";
		doConnect();
		
		try {
		
			String name = user.getName();
			String account = user.getAccount();
			String password = user.getPassword();
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getAccount());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, "");
			pstmt.setInt(5, user.getActive());
			
			int i = pstmt.executeUpdate();
			if( i != 0) {
				System.out.println("addUser()=>" + user.toString());
				doClose();
			}
			

		}catch (Exception e) {
			System.out.println("addUser()���~:" + e.toString());

		}
	}
	
	
	
	
}

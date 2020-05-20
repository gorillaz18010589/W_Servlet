package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import beans.GoogleSiginAccoutBean;
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
			System.out.println("addUser()失敗:" + e.toString());

		}
	}
	
	public void addGoogleSiginAccount(GoogleSiginAccoutBean googleSiginAccoutBean) {
		doConnect();
		String sql ="INSERT INTO google_signin_account(wid,returna,noun,description,par,type,clazz,extend) VALUES (?,?,?,?,?,?,?,?)";
		
		String wid = googleSiginAccoutBean.getwId();
		String returna = googleSiginAccoutBean.getReturna();
		String noun = googleSiginAccoutBean.getNoun();
		String description = googleSiginAccoutBean.getDescription();
		String par = googleSiginAccoutBean.getPar();
		String type = googleSiginAccoutBean.getType();
		String clazz = googleSiginAccoutBean.getClazz();
		String extend = googleSiginAccoutBean.getExtend();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, wid);
			pstmt.setString(2, returna);
			pstmt.setString(3, noun);
			pstmt.setString(4, description);
			pstmt.setString(5, par);
			pstmt.setString(6,type);
			pstmt.setString(7,clazz);
			pstmt.setString(8, extend);
			
			int i = pstmt.executeUpdate();
			if(i != 0) {
				System.out.println("addGoogleSiginAccount()成功新增:" + i +"資料");
				doClose();
			}

		}catch (Exception e) {
			System.out.println("addGoogleSiginAccount()失敗:" + e.toString());
		}

	}
	
	
	
	
	
	
	
}

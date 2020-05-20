package job;
//目的:ExcleDBAPI
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.GoogleSiginAccoutBean;
import model.DB;

public class JobDB {
	private final static String DEFAULT_TABLE_NAME =" google_signin_account ";
	private final static String USER ="root";
	private final static String PASS_WORD ="root";
	private final static String JDBC ="com.mysql.cj.jdbc.Driver";
	private final static String DB_NAME ="text";
	private final static String SERVER_TIMEZONE ="?serverTimezone=Asia/Taipei";
	private final static String URL ="jdbc:mysql://localhost:3307/" + DB_NAME + SERVER_TIMEZONE;
	private Connection con;
	
	private ArrayList<GoogleSiginAccoutBean> googleSiginAccoutList = new ArrayList<>();
	
	public void doConnect() {
		try {
			Class.forName(JDBC);
			con = DriverManager.getConnection(URL, USER, PASS_WORD);
			System.out.println("doConnect():����");
		}catch (Exception e) {
			System.out.println("doConnect()憭望��:" + e.toString());
		}	
	}
	
	public void doClose() {
		try {
			con.close();
			System.out.println("doClose():����");
		} catch (Exception e) {
			System.out.println("doClose()憭望��:" + e.toString());
		}
	}
	
	
	/*1.�憓xcelWord瑼���DB
	 * @pamram:String fileName => 1.閬��DB��sv瑼��蔭
	 * @param:Object object => 2.��ervlet��bjext,銝餉�閬getClass�瘜�
	 * */
	public  void addExcleWord(String fileName, Object object) throws ServletException, IOException {
			addExcleWord(DEFAULT_TABLE_NAME, fileName, object);
			
	}
	
	/*2.�憓xcleWord瑼���DB,�隞交��澈���
	 * @param:String tableName	=> 1.閬◤���DB��”
	 * @param:String fileName => 2.閬��DB��sv瑼��蔭
	 * @param:Object object => 3.��ervlet��bjext,銝餉�閬getClass�瘜� */
	public  void addExcleWord(String tableName, String fileName, Object object) throws ServletException, IOException {
		try {
			 String[] splitLine;
		     String [] row;
		     
			 doConnect();
			 
		     URL url =
		    		 object.getClass().
		    		  getClassLoader().
		    		 	getResource(fileName);
		     
		     String path = url.getPath(); //������
		     System.out.println(path);
		     InputStream in = new FileInputStream(path); //���撓�瑼��葡瘚�(閬撓������)
		     
		     //霈�����葡瘚�
		     BufferedReader boBufferedReader = new BufferedReader(new InputStreamReader(in));
		     String line = null;
		     

		     boBufferedReader.readLine();
		     while ((line = boBufferedReader.readLine()) !=null ) {
		    	 	System.out.println(line);

		    	 	int firstIndex = line.indexOf("(");
		    	 	int lastIndex = line.indexOf(")");
		    	 	
		    	 		if(firstIndex != -1) {
		    	 			
		    	 				String methond = line.substring(firstIndex, lastIndex +1);
				    	 		System.out.println(firstIndex + "=>"  +lastIndex);
				    	 		
				    	 		splitLine = line.split(",");
					    	 	 
						        String row0 = splitLine[0];
						    	String row1 = splitLine[1];
							    String row2 = splitLine[2];
							    String row3 = splitLine[3];
							    String row4 = splitLine[4];
							    String row5 = splitLine[5];
							    String row6 = splitLine[6];
							    String row7 = splitLine[7];
							    
							    //憒�洵鈭��葡��.
							    if(methond.contains(".")) {
							    	row2 = row2.replace(".",",");
					    	 		System.out.println("new row2:" + row2);
				    	 		}
			    	 		
							    System.out.println("row0:" + row0 + "/row1:" + row1 + "/row2:" + row2 + "/row3:" + row3 + "/row4:" + row4 + "/row5:" + row5 + "/row6:" + row6 + "/row7:" + row7);
							    System.out.println("==========================");

							    String sql = "INSERT INTO"  + tableName + "(wid,returna,noun,description,par,type,clazz,extend) VALUES (?,?,?,?,?,?,?,?)";							    
							    PreparedStatement pstmt = con.prepareStatement(sql);
							    pstmt.setString(1, row0);
							    pstmt.setString(2, row1);
							    pstmt.setString(3, row2);
							    pstmt.setString(4, row3);
							    pstmt.setString(5, row4);
							    pstmt.setString(6, row5);
							    pstmt.setString(7, row6);
							    pstmt.setString(8, row7);
							    
							    
							    int count = pstmt.executeUpdate();
							    
							    if(count != 0) {
							    	System.out.println("������:" + count +"蝑�");
							    }
							   
							    
		    	 		}
			    	
		     }
		     
		     doClose();
		     boBufferedReader.close();
		   
		   
		}catch (Exception e) {
			System.out.println( "�隤�"+e.toString());
			
		}
	}
	
	public ArrayList<GoogleSiginAccoutBean> getExcleWord() {
		try {
			doConnect();
			String sql ="SELECT * FROM google_signin_account";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			ResultSet resultSet = pstmt.executeQuery();
			
			while(resultSet.next()) {
				String wid = resultSet.getString("wid");
				String returna = resultSet.getString("returna");
				String noun = resultSet.getString("noun");
				String description	 = resultSet.getString("description");
				String par = resultSet.getString("par");
				String type = resultSet.getString("type");
				String clazz = resultSet.getString("clazz");
				String extend = resultSet.getString("extend");

				System.out.println(wid + returna + noun + description + par + type + clazz + extend);
				
				GoogleSiginAccoutBean googleSiginAccoutBeans = new GoogleSiginAccoutBean();
					googleSiginAccoutBeans.setwId(wid);
					googleSiginAccoutBeans.setReturna(returna);
					googleSiginAccoutBeans.setNoun(noun);
					googleSiginAccoutBeans.setDescription(description);
					googleSiginAccoutBeans.setPar(par);
					googleSiginAccoutBeans.setType(type);
					googleSiginAccoutBeans.setClazz(clazz);
					googleSiginAccoutBeans.setExtend(extend);
				
					googleSiginAccoutList.add(googleSiginAccoutBeans);
					googleSiginAccoutBeans = null;
			}
			
			doClose();

		}catch (Exception e) {
			System.out.println("getExcleWord()憭望��:" +e.toString());
		}		
		return googleSiginAccoutList;
	}
}

package text;
//A.建立resouser存放檔案

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
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.GoogleSiginAccoutBean;
import model.DB;
import model.GsonUtils;



@WebServlet("/readExcel")
public class readExcel extends HttpServlet {
       private String[] splitLine;
       private String [] row;
       private DB db;
     
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doTask(req, resp);
	}
	
	protected void doTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			
			//1.DB連線
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties properties = new Properties();
			properties.put("user", "root");
			properties.put("password","root");
			properties.put("serverTimezone","Asia/Taipei");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/text", properties);
			
		
			GoogleSiginAccoutBean accountBean = new GoogleSiginAccoutBean();
		    //檔案路徑名稱
			String fileName = "config/g.csv";
			String keyWord ="";
			
			//設定輸出格式
			response.setCharacterEncoding("utf-8");
			response.setContentType("application/vnd.ms-excel");  
			response.addHeader("Content-Disposition", "attachment;filename=fileName"+".xls");
			PrintWriter printWriter = response.getWriter();
//			file:/C:/hank/javaee/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/MyJaveEE/WEB-INF/classes/config/text.txt
			
		     URL url =
		    		//取得這個類別 
		    		 getClass(). //class text.readExcel
		    		 //取得這個專案的res層級
		    		  getClassLoader().
		    		  //取得這個ClassLoader裡面的resouser(你指定的檔案名)
		    		 	getResource(fileName);
		     System.out.println(getClass().getClassLoader());
		     String path = url.getPath(); //取得這個頁面
		     System.out.println(path);
		     InputStream in = new FileInputStream(path); //取得輸入檔案的串流(要輸入的檔案頁面)
		     
		     //讀取檔案串流
		     BufferedReader boBufferedReader = new BufferedReader(new InputStreamReader(in));
		     String line = null;
		     
		     boBufferedReader.readLine();//先讀一行,第一列的名稱不要
		     while ((line = boBufferedReader.readLine()) !=null ) {
		    	 	System.out.println(line);

		    	 	//取得()字元的index位置
		    	 	int firstIndex = line.indexOf("("); 
		    	 	int lastIndex = line.indexOf(")");
		    	 	
		    	 		//如果-1,代表已經沒查詢到
		    	 		if(firstIndex != -1) { 
		    	 			
		    	 				//取得((Parcel out, int flags)內的字串
		    	 				String methond = line.substring(firstIndex, lastIndex +1);
				    	 		System.out.println(firstIndex + "=>"  +lastIndex);
				    	 		
				    	 		//切割逗號
				    	 		splitLine = line.split(",");
					    	 	 
				    	 		
						        String row0 = splitLine[0];
						    	String row1 = splitLine[1];
							    String row2 = splitLine[2];
							    String row3 = splitLine[3];
							    String row4 = splitLine[4];
							    String row5 = splitLine[5];
							    String row6 = splitLine[6];
							    String row7 = splitLine[7];
							    
							    //如果第二個字串包刮.將.改成逗號,在存放到row2
							    if(methond.contains(".")) {
							    	row2 = row2.replace(".",",");
					    	 		System.out.println("new row2:" + row2);
				    	 		}
			    	 		
							    System.out.println("row0:" + row0 + "/row1:" + row1 + "/row2:" + row2 + "/row3:" + row3 + "/row4:" + row4 + "/row5:" + row5 + "/row6:" + row6 + "/row7:" + row7);
							    System.out.println("==========================");

							    
								String sql ="INSERT INTO google_signin_account(wid,returna,noun,description,par,type,clazz,extend) VALUES (?,?,?,?,?,?,?,?)";
							    PreparedStatement pstmt = connection.prepareStatement(sql);
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
							    	System.out.println("新曾成功:" + count +"筆");
							    	pstmt.close();
							    }
							   
							    
		    	 		}
			    	
		     }
		     

		     printWriter.flush();
		     printWriter.close();
		     boBufferedReader.close();
		   
		   
		}catch (Exception e) {
			System.out.println( "錯誤"+e.toString());
			
		}
		
	}

}

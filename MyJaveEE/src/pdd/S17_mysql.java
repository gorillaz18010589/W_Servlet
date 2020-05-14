package pdd;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;



@WebServlet("/S17_mysql")
public class S17_mysql extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String name,account,password;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doTask(req,resp);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

	
	protected void doTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			
			//1.DB連線
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties properties = new Properties();
			properties.put("user", "root");
			properties.put("password","root");
			properties.put("serverTimezone","Asia/Taipei");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/iii", properties);
			
			//網頁form方式取得參數 =>AS來說是doFormBody
//			String name = request.getParameter("name");
//			String account = request.getParameter("account");
//			String password = request.getParameter("password");
//			String localeName = request.getLocalName();
			
//			System.out.println("�ϥΪ̦���J:" +"name:" + name + "account:" + account +"password:" + password +"localeName:" + localeName);
			
			//2.接收AS傳來的Json物件的request方式
			BufferedReader bufferedReader = request.getReader();
			String json = bufferedReader.readLine();
			System.out.println(json);
			bufferedReader.close();
			
			//3.解析JSON{"password":"152132","name":"android","account":"123456@gmail.com"}
			JSONObject jsonObject = new JSONObject(json);
			for(int i=0; i<jsonObject.length(); i++) {
				name = jsonObject.getString("name");
			    account = jsonObject.getString("account");
			    password = jsonObject.getString("password");
			    System.out.println("解析成功" +"name:" + name + "/account:" + account +"/password:" + password );

			}
			

			//4.將拿到的參數加入到DB
			String sql ="INSERT INTO user (name,account,password) VALUES(?,?,?)";
			PreparedStatement preparedStatement =  connection.prepareStatement(sql);
			preparedStatement.setString(1,name);
			preparedStatement.setString(2,account);
			preparedStatement.setString(3,password);
			int count = preparedStatement.executeUpdate();
			System.out.println(count);
			System.out.println("dbOK");
		
		} catch (Exception e) {
				System.out.println("失敗的話" + e.toString());
		}
	}
}

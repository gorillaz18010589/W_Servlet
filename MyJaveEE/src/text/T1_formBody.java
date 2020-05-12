package text;
//https://blog.csdn.net/luckey_zh/article/details/61197587
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;
import org.json.JSONObject;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mysql.cj.protocol.Resultset.Type;

import jdk.nashorn.internal.parser.JSONParser;
import model.DB;
import model.ResultWriter;
import netscape.javascript.JSObject;


@WebServlet("/T1_formBody")
public class T1_formBody extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doTask(req,resp);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("doTask()");
		
		//設定要給clent端的輸出格式
		response.setContentType("application/json, charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		
		//資料庫連接
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties properties = new Properties();
			properties.put("user", "root");
			properties.put("password","root");
			properties.put("serverTimezone","Asia/Taipei");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/iii", properties);
			
			//接收網頁參數form的辦法,用getParameter,取得AS傳來參數
			String name = request.getParameter("name");
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			String localeName = request.getLocalName();
			System.out.println("使用者有輸入:" +"name:" + name + "/account:" + account +"/password:" + password +"localeName:" + localeName);
			
			
			//將取得參數新增到DB
			String sql ="INSERT INTO user(name, account, password) VALUES(?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, account);
			preparedStatement.setString(3, password);
			int count = preparedStatement.executeUpdate();
			
			if(count == 1) {
//				//1.傳給Client的資料結構為 =>{"msg":["OK"]}
//				JSONObject jsonObject = new JSONObject();
//				jsonObject.append("msg", "OK");
//				printWriter.append(jsonObject.toString());
//				
//				//2..傳給Client的資料結構為 =>{"msg:OK"}
//				Gson gson = new Gson();
//				Map<String, String> map = new HashMap();
//				map.put("msg", "ok");
//				String jsonMsg = gson.toJson(map);
//				System.out.println(jsonMsg);
//				printWriter.write(jsonMsg);
				
				
				//3.自己寫的API將response的msg用Json格式送出
//				Map<String, String> map = new HashMap();
//				map.put("msg", "ok");
//				ResultWriter.write(response, map);
				
				//4.自己寫的直接傳送{"msg":"OK"}方法
				ResultWriter.writeOk(response);
				
			}
			printWriter.flush();//重要不然會漏訊息
			System.out.println("DB insert成功:" + count +"筆");
			
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		
		
	}
}

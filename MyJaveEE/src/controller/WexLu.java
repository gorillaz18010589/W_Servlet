package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import beans.UserBean;
import model.CheckUtils;
import model.DB;
import model.GsonUtils;
import model.JwtUtils;
import model.ResultWriter;
import model.WordUtils;

/**
 * Servlet implementation class WexLu
 */
@WebServlet("/WexLu")
public class WexLu extends HttpServlet {
       
   
	@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doTask(req, resp);
		}


	protected void doTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//1.取得client端的參數串流
		BufferedReader bufferedReader = request.getReader();
		String json = bufferedReader.readLine();
		bufferedReader.close();
		System.out.println("Json參數串流:" +json);
		
		String page = request.getParameter("page");
		System.out.println("page:" +page);
		String action = GsonUtils.getParamString(json, "action");
		System.out.println("action:" +action);

		
		//**如果client端的action參數沒有為空而且有串流進來
		if(action != null && json != null) {
			
			//A.如果使用者呼叫註冊API
			if(action.equals("register")) {
				try {
					
					//1.取得client端傳來的指定參數
							String account = GsonUtils.getParamString(json, "account");
							String password = GsonUtils.getParamString(json, "password");
							String name = GsonUtils.getParamString(json, "name");
							
					//2.檢驗帳號,密碼,信箱
							if(!CheckUtils.isInput(name)) {	ResultWriter.writeMsg(response, "姓名沒輸入"); }
							else if(!CheckUtils.isInput(account)) {	ResultWriter.writeMsg(response, "帳號沒輸入");}
							else if(!CheckUtils.isInput(password)) {	ResultWriter.writeMsg(response, "密碼沒輸入");}
							else if(!CheckUtils.isEmail(account)) {	ResultWriter.writeMsg(response, "email格式錯誤");}
//							else if(password.length() >12 || password.length() <5) {ResultWriter.writeMsg(response, "密碼必須大於4且小於12瑪");}
							
					//3.如果檢驗都成功的話,讓他註冊,並且寄激活信		
							else {
								UserBean userBean = GsonUtils.jsonStringToBean(json, UserBean.class);
								DB db = new DB(response);
								String registerMsg = db.addUser(userBean);
								
								//如果使用者註冊成功,取得成功訊息
								if(registerMsg.equals("success")) {
									System.out.println("註冊成功!");
								}	
								
							}
		
				}catch (Exception e) {
					System.out.println("register失敗:" + e.toString());
				}
						
			}
		
		} 
		
		//EX.如果page有得到
		if(page != null) { 
			 //如果使用者激活了信件的話,收到userEmail,hashCode,page三個參數
			if(page.equals("mymail")) {
				
				
				String userEmail = request.getParameter("key1");
				String hasCode = request.getParameter("key2");
				
				try {
				 String token =	JwtUtils.createToken(userEmail, hasCode);
				 
				} catch (Exception e) {
					e.printStackTrace();
				}

				System.out.println("email:激活成功");
			}
		}
		
	}
		
		
		
}



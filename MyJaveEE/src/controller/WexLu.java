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
		System.out.println(json);
		
		
//		//A.用Gson方式輸入Json物件,取得節點的資料 {"account":"cf","active":"0","name":" cg","password":"ff"}
//		JsonParser jsonParser = new JsonParser();//取得json解析器物件
//		JsonElement jsonElement = jsonParser.parse(json); //取得JsonElement物件實體
//		System.out.println(jsonElement);//{"account":"h","active":"0","name":"g","password":"b"}
//		
//		JsonObject jsonObject  = jsonElement.getAsJsonObject(); //取得JsonObject()實體
//		JsonElement accoubtElement = jsonObject.get("account"); //取得指定節點的資料(要查詢的節點字串)回傳到JsonElement
//		System.out.println(accoubtElement.toString()); //h
//		
	
		
		try {
			
			//2.取得client端傳來的指定參數,回傳Strng
			String account = GsonUtils.getParamString(json, "account");
			String password = GsonUtils.getParamString(json, "password");
			String name = GsonUtils.getParamString(json, "name");
			
			
			System.out.println(account);
			System.out.println(name);
			System.out.println(password);
			
			//名稱沒輸入
			if(!CheckUtils.isInput(name)) {	ResultWriter.writeMsg(response, "姓名沒輸入");}
			if(!CheckUtils.isInput(account)) {	ResultWriter.writeMsg(response, "帳號沒輸入");}
			if(!CheckUtils.isInput(password)) {	ResultWriter.writeMsg(response, "密碼沒輸入");}
			
			if(!CheckUtils.isEmail(account)) {ResultWriter.writeMsg(response, "email格式錯誤");}
			
			ResultWriter.writeMsg(response, "使用者有輸入");
			
			
//			UserBean userBean = GsonUtils.jsonStringToBean(json, UserBean.class);
//			DB db = new DB(response);
//			db.addUser(userBean);
			
			
			
			
		}catch (Exception e) {
			System.out.println("帳密失敗:" + e.toString());
		}
		
		

		
		}
		
		
		
	}



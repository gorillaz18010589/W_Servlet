package model;
//fromJson(String json, Type typeOfT):從Json字串解析回傳到資料結構上(1.要轉型的Json字串,2.要轉型的資料結構類型)(回傳<T> T)

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ResultWriter {
	private static String defalutContentType ="application/json, charset=UTF-8"; 
	private static Map<String,String> map = new HashMap(); 
	
	/*1.response訊息設定
	 * @param HttpServletResponse response => 1.Servlet的Response
	 * @param Map map => 2.要傳遞得訊息key:value
	 * @param String contentType => 3.contentType格式
	 * */
	public static void write(HttpServletResponse response, Map map, String contentType) throws Exception {
		Gson gson = new Gson();
		String jsonMsg = gson.toJson(map);
		PrintWriter printWriter = response.getWriter();
		printWriter.write(jsonMsg);
		printWriter.close();
		System.out.println("ResultWriter.write()" +"/jasonMsg:" + jsonMsg);
	}
	
	/*2.response訊息設定,預設contentTyep ="application/json, charset=UTF-8"
	 * @param HttpServletResponse response => 1.Servlet的Response
	 * @param Map map => 2.要傳遞得訊息key:value
	 * */
	public static void write(HttpServletResponse response, Map map) throws Exception {
		write(response, map,defalutContentType);
	}
	
	/*3.response訊息OK
	 * @param HttpServletResponse response => 1.Servlet的Response
	 * */
	public static void writeOk(HttpServletResponse response) throws Exception {
		map.put("msg", "OK");
		write(response, map,defalutContentType);
	}
	
	
	/*4.javaBeanToMap
	 * @param Object javabean => 要轉型成Map的JavaBean
	 * */
    public static Map<String, String> javaBeanToMap(Object javabean) {
        Gson gson = new Gson();
        String json = gson.toJson(javabean);
        Map<String, String> map = gson.fromJson(json, new TypeToken<Map<String, String>>() {
        }.getType());
        return map;
    }

}

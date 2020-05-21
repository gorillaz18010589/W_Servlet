package model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import beans.ApiRequest;

public class GsonUtils   {
	
	

	/*1.將JsonString轉成Bean
	 * @pamram String json = > 1.要轉成bean的Json資料字串
	 * @pamram Class<T> clazz  2.要存放的bean類別
	 * */
    public static <T> T jsonStringToBean(String json, Class<T> clazz) {
        T t = null;
        Gson gson = new Gson();
        t = gson.fromJson(json, clazz);
        System.out.println("jsonStringToBean()" + "/json:" + json  +"class:" +clazz.toString());
        return t;
    }
    
    /*2.將物件轉成Json
	 * @pamram Object object = > 1.要轉被轉成json的物件
	 * */
    public static String toJson(Object object) {
        Gson gson = new Gson();
        String json =gson.toJson(object);
    	return json;
    }
    
    //3.輸入指定的字串節點取得該節點的資料
    public static String getParamString(String json, String param) {
    	
    	JsonParser jsonParser = new JsonParser();
    	JsonElement jsonElement = jsonParser.parse(json);
    	JsonObject jsonObject = jsonElement.getAsJsonObject();
    	JsonElement valueElement = jsonObject.get(param);
    	String hso = valueElement.getAsString();
    	
    	return hso;
    	
    }

}

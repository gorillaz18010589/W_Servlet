package model;

import com.google.gson.Gson;

import beans.ApiRequest;

public class GsonUtils   {
	
	

    public static <T> T jsonStringToBean(String json, Class<T> clazz) {
        T t = null;
        Gson gson = new Gson();
        t = gson.fromJson(json, clazz);
        System.out.println("jsonStringToBean()" + "/json:" + json  +"class:" +clazz.toString());
        return t;
    }
    
    public static String toJson(Object object) {
        Gson gson = new Gson();
        String json =gson.toJson(object);
    	return json;
    }

}

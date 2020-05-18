package model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public  class RequestUtils {
	
	
	
	/*1.取得所有網頁輸入的參數
	 * @param : HttpServletRequest request => 使用者傳來的request
	 * 回傳 [{key:value}] //[{"startIndex":"1","endIndex":"11",}]
	 * */
	public static ArrayList<Map<String, String>> getParameterListMap(HttpServletRequest request){
		Map<String,String> wordsMap = new HashMap();
		ArrayList<Map<String, String>> wordsList = new ArrayList();
		
		Enumeration<String> names = request.getParameterNames();
		while(names.hasMoreElements()) {
			String name = names.nextElement();
			String value = request.getParameter(name);
			System.out.println(name +":" + value);
			wordsMap.put(name, value);
		}
		
    	wordsList.add(wordsMap);
    	
    	return wordsList;
	}

	
}

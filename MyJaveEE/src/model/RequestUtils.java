package model;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public  class RequestUtils {
	
	
	
	/*1.���o�Ҧ�������J���Ѽ�
	 * @param : HttpServletRequest request => �ϥΪ̶ǨӪ�request
	 * �^�� [{key:value}] //[{"startIndex":"1","endIndex":"11",}]
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

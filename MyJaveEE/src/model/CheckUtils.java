package model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckUtils {
	
	/*判斷是否是email格式
	 * @pamram String email => 要判斷的email
	 * */
	public static boolean isEmail(String email) {
		if (email == null)
			return false;
		String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern p;
		Matcher m;
		p = Pattern.compile(regEx1);
		m = p.matcher(email);
		if (m.matches())
			return true;
		else
			
			return false;
	}
	
	
	/*判斷多個字串是否有輸入,且不為空
	 * @parma String[] str => 要判斷是否有輸入的字串陣列
	 * */
	public static boolean isInput(String ... str) {
		boolean isInput = false;
		
		for(int i=0; i< str.length; i++) {
			isInput = isOk(str[i]);
			if(isInput == false) {
				break;
			}else {
				isInput = true;
			}
		}
		
		return isInput;
	}
	

	/*判斷單一字串是否有輸入且不為null
	 * @parma String msg => 要判斷是否有輸入的字串
	 * */
	public static boolean isOk(String msg) {
		boolean isOK = true;
		if(msg == null || msg.isEmpty()) {
			isOK = false;
			System.out.println("空");
		}else {
			System.out.println("有");
		}
		return isOK;
	}
	
}

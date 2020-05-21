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
	
	
	/*判斷使用者字串是否有輸入
	 * @param String inpuTex => 要檢查是否有輸入的字串
	 * return => true有輸入,false沒輸入
	 * */
	public static boolean isInput(String inpuText) {
		
		boolean isInput = true;
		
		if(inpuText != null) {
			
			if(inpuText == null) isInput = false;
			if(inpuText.equals("")) isInput =false;
			
		}else {
			isInput = false;
		}
	
		
		return isInput;
	}
	
	
}

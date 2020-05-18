package model;

import java.awt.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

public class WordUtils {
	
	/*將excelWord的格式轉成javWrod格式
	 * @param:String word =>要被轉型javaWord的字串excel格式
	 */
	public static String excelWordToJavaWord(String word) {
		StringBuilder sb = new StringBuilder();
		if(!word.isEmpty() && word != null) {
			String newWord = word.replaceAll("\\s", "-");
			System.out.println(newWord);
		
			
			String[] splitWord = newWord.split("-");
			
			String s0 = splitWord[0];
			String s1 = splitWord[1];
			String s2 = splitWord[2];
			String s3 = splitWord[3];
			System.out.println("s0:" + s0 +"/s1:" + s1 +"/s2:" +s2 +"/s3:" +s3);
			
			splitWord[3] = s1;
			splitWord[1] = s2;
			splitWord[2] = s3;
			
			
			String a0 = splitWord[0];
			String a1 = splitWord[1];
			String a2 = splitWord[2];
			String a3 = splitWord[3];
			
			sb.append(a0+"." +a1 +"=>" + a2 + "(回傳值:" + a3 +")");
			System.out.println(sb);
			System.out.println("=====================");
		}
		
		
		return sb.toString();
	
	}
	
	/*將多個excelWord的格式轉成javWrod格式
	 * @param : string..  => 要改格式的word
	 */
	public static ArrayList<String> excelWordToJavaWords(String word0, String word1 , String word2, String word3, String word4, String word5, String word6, String word7) {
		
		String w0 = excelWordToJavaWord(word0);
		String w1 = excelWordToJavaWord(word1);
		String w2 = excelWordToJavaWord(word2);
		String w3 = excelWordToJavaWord(word3);
		String w4 = excelWordToJavaWord(word4);
		String w5 = excelWordToJavaWord(word5);
		String w6 = excelWordToJavaWord(word6);
		String w7 = excelWordToJavaWord(word7);

		
		ArrayList<String> arrayList = new ArrayList();
		arrayList.add(w0);
		arrayList.add(w1);
		arrayList.add(w2);
		arrayList.add(w3);
		arrayList.add(w4);
		arrayList.add(w5);
		arrayList.add(w6);
		arrayList.add(w7);
		

		return arrayList;
	
	}

	
	public static String excelBlankWordToJavaWord(String word, String startIndex, String endIndex) {
		
			String answerWord = null;
				
			if(!word.isEmpty() && word != null) {
			System.out.println("有包含");
			int intX = Integer.parseInt(startIndex);
			int intY = Integer.parseInt(endIndex);

			String newWord = word.substring(intX, intY);
			System.out.println(newWord);
			String blankWord = newWord.replaceAll("\\s", "-");
			String newBlankWord = blankWord.replace("<", "{");
			
			String repalceWrod = word.replaceAll(newWord, "");
			System.out.println(repalceWrod);
			
			StringBuilder sb = new StringBuilder();
			sb.append("(回傳值:" + newBlankWord +")");
			String keyWord = repalceWrod.concat(sb.toString());
			System.out.println(keyWord);
			
			
			char fistWord = keyWord.charAt(0);
			System.out.println(keyWord);
			String a = keyWord.substring(1,2);
			String c = keyWord.replace(a, "*");
			System.out.println(c);
			
			
			String[] endWord = c.split("\\*");
			for(String aa: endWord) {
				System.out.println(aa);
			}
			
			String s0 = endWord[0]; //9
			String s2 = endWord[2]; //inflate(LayoutInflater inflater, int layoutId, ViewGroup parent, boolean attachToParent)
			String s3 = endWord[3];	//fragment綁定Layout(1.綁定的Layout,2.要綁定的LayoutId 3.父類別的ViewGroup 4.是否直接將layout的元件加入到父類別畫面)(回傳值:static <T extends ViewDataBinding> T)
			StringBuilder sb2 = new StringBuilder();
			System.out.println(s3);
			sb2.append(fistWord +"." + s2 +"<br>" +s3);
			answerWord = sb2.toString();
			System.out.println(answerWord);
		}
		
		return answerWord;
		
	}

	
	public static ArrayList<String> excelBlankWordToJavaWords(String word0,String word1,String word2,String word3,String word4,String word5,String word6,String word7,String word8, String startIndex, String endIndex) {
		
		String w0 = excelBlankWordToJavaWord(word0, startIndex, endIndex);
		String w1 = excelBlankWordToJavaWord(word1, startIndex, endIndex);
		String w2 = excelBlankWordToJavaWord(word2, startIndex, endIndex);
		String w3 = excelBlankWordToJavaWord(word3, startIndex, endIndex);
		String w4 = excelBlankWordToJavaWord(word4, startIndex, endIndex);
		String w5 = excelBlankWordToJavaWord(word5, startIndex, endIndex);
		String w6 = excelBlankWordToJavaWord(word6, startIndex, endIndex);
		String w7 = excelBlankWordToJavaWord(word7, startIndex, endIndex);
		String w8 = excelBlankWordToJavaWord(word8, startIndex, endIndex);
		
		ArrayList<String> words = new ArrayList<>();
		words.add(w0);
		words.add(w1);
		words.add(w2);
		words.add(w3);
		words.add(w4);
		words.add(w5);
		words.add(w6);
		words.add(w7);
		words.add(w8);

		return words;
	}

  
    		 
    
    

}

package model;

import java.util.ArrayList;

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

}

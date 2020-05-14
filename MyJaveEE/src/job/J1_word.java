package job;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.WordUtils;



@WebServlet("/J1_word")
public class J1_word extends HttpServlet {
       
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//設定Client傳回來的request可以用中文編碼
		request.setCharacterEncoding("UTF-8");
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		
		String word0 = request.getParameter("word0");
		String word1 = request.getParameter("word1");
		String word2 = request.getParameter("word2");
		String word3 = request.getParameter("word3");
		String word4 = request.getParameter("word4");
		String word5 = request.getParameter("word5");
		String word6 = request.getParameter("word6");
		String word7 = request.getParameter("word7");

		//1.只能抓一個Input的方法
//		String newWord = WordUtils.excelWordToJavaWord(word0);
//		printWriter.write(newWord);
		
		StringBuilder sb = new StringBuilder();
		ArrayList<String> words = WordUtils.excelWordToJavaWords(word0, word1, word2, word3, word4, word5, word6, word7);
		for(String s : words) {
			printWriter.append(s +"<br>");
		}
	
		printWriter.flush();
		printWriter.close();
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

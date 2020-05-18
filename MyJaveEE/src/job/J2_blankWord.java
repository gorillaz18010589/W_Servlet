package job;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.util.RequestUtil;

import com.google.gson.Gson;

import model.GsonUtils;
import model.RequestUtils;
import model.ResultWriter;
import model.WordUtils;


@WebServlet("/J2_blankWord")
public class J2_blankWord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doTask(req, resp);
	}
	
	protected void doTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		
		request.setCharacterEncoding("UTF-8");


//		String word0 = request.getParameter("word0");
//		String word1 = request.getParameter("word1");
//		String word2 = request.getParameter("word2");
//		String word3 = request.getParameter("word3");
//		String word4 = request.getParameter("word4");
//		String word5 = request.getParameter("word5");
//		String word6 = request.getParameter("word6");
//		String word7=  request.getParameter("word7");
//		String word8 = request.getParameter("word8");
//
//
//		String startIndex = request.getParameter("startIndex");
//		String endIndex = request.getParameter("endIndex");
//		
//		
//		ArrayList<String> a = WordUtils.excelBlankWordToJavaWords(word0, word1, word2, word3, word4, word5, word6, word7, word8, startIndex, endIndex);
//		for(String s : a) {
//			printWriter.append(s +"<br>" +"<br>");
//		}

		
		
		    	
    	
    	

	
		
		
	}

}

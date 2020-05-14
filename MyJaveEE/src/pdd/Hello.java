package pdd;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//1
//2
@WebServlet("/Hello")
public class Hello extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Hello() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter printWrite = response.getWriter();
		printWrite.append("hello");
		
		//取得標頭檔的name跟value
		Enumeration<String> headers = request.getHeaderNames();
		while(headers.hasMoreElements()) {
			String headeName = headers.nextElement();
			String value = request.getHeader(headeName);
			System.out.println("headName:" + headeName +"/value:" + value);
		}
		
		
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	

}

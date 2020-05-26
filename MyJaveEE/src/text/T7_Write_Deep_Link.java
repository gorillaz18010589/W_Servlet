package text;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/T7_Write_Deep_Link")
public class T7_Write_Deep_Link extends HttpServlet {
	
	
	@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doTask(req, resp);
		}
	
	protected void doTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		 //目的:測試可以用write輸出寫deep_link
		 response.setContentType("text/html; charset=UTF-8"); 
		 response.setCharacterEncoding("UTF-8");
		 PrintWriter printWriter = response.getWriter();
		 
		 printWriter.write("<html>");
		 printWriter.write("<a href=\"app://w_deep_link\"/> deep link  </a>");
		 printWriter.write("</html>");
	}

}

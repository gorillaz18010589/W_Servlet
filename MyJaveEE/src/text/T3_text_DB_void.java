package text;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.DB;

@WebServlet("/T3_text_DB_void")
public class T3_text_DB_void extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doTask(req,resp);
	}
	

	protected void doTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DB db = new DB();
		Gson gson = new Gson();
		
	}

}

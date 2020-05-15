package text;
//20.getReader()=>取得requset的資料串流(適用於fomrbody接收傳來的參數)(回傳值:BufferedReader)


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;

import com.google.gson.Gson;

import beans.UserBean;
import model.DB;
import model.GsonUtils;

@WebServlet("/T3_text_DB_void")
public class T3_text_DB_void extends HttpServlet {
	private static final long serialVersionUID = 1L;
  

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doTask(req,resp);
	}
	

	protected void doTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			response.setContentType("text/html, charset=UTF-8");
			PrintWriter printWriter = response.getWriter();
			
			request.setCharacterEncoding("UTF-8");
			
			BufferedReader bufferedReader = request.getReader();
			String json = bufferedReader.readLine();
			bufferedReader.close();
			System.out.println(json);  //{"password":"ff","name":"hank","active":"2","account":"gg","hash":"w"}
			
//			UserBean user = gson.fromJson(json, UserBean.class);
			UserBean user = GsonUtils.jsonStringToBean(json, UserBean.class);
			System.out.println(user.toString());
			
			DB db = new DB();
			db.addUser(user);
			
			
			
			
	}

}

package text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/T4_ServletRequest物件使用")
public class T4_ServletRequest物件使用 extends HttpServlet {
 
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doTask(req, resp);
	}
	
	protected void doTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			response.setContentType("text/html, charset=UTF-8");
			PrintWriter printWriter = response.getWriter();
		
			request.setCharacterEncoding("UTF-8");
			
			
			BufferedReader bufferedReader = request.getReader(); //取得requset的資料串流(適用於fomrbody接收傳來的參數)
			String json = bufferedReader.readLine();
			System.out.println(json);
			
			request.setAttribute("hank", "18"); //�]�w�ݩʥi�ΨӶǻ�������(key:value)
			
			int ContentLength = request.getContentLength(); //���o�ɮפW�Ǥj�p
			System.out.println("ContentLength:" + ContentLength);
			
			
			String characterEncoding = request.getCharacterEncoding(); //���o�Τ�s�X����
			System.out.println("characterEncoding:" + characterEncoding);//characterEncoding:UTF-8
			
			
			String localAddr = request.getLocalAddr();//���o�Τ�IP��m
			System.out.println("localAddr:" +localAddr); //localAddr:10.0.8.78
		
			
			Locale locale = request.getLocale(); //���o�Τ�a�Ϫ���
			System.out.println("locale:" + locale); //locale:zh_TW
			
			String localeName = request.getLocalName();//���o�Τ�ip,host���D���W��
			System.out.println("localeName:" + localeName);//localeName:DESKTOP-ACM8S32
			
			int localePort = request.getLocalPort();//���o�Τ�port��
			System.out.println("localePort:" + localePort); //localePort:8080
			
			String protocol = request.getProtocol();//���o�Τ�Http��ĳ
			System.out.println("protocol:" + protocol); //protocol:HTTP/1.1
			
			String serverName = request.getServerName();//���o�Τ�Http��ĳ
			System.out.println("serverName:" + serverName); //protocol:HTTP/1.1
			
			
		
	}

}
